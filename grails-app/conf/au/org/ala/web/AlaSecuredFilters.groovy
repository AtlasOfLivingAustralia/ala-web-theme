package au.org.ala.web

import java.lang.reflect.Method
import java.lang.reflect.Modifier

/**
 * Looks for {@link AlaSecured} annotations on the requested Controller and action and performs role based
 * authorised as specified on the annotation.
 */
class AlaSecuredFilters {

    def grailsApplication
    def securityPrimitives

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                def controller = grailsApplication.getArtefactByLogicalPropertyName("Controller", controllerName)
                Class cClazz = controller?.clazz

                if (!cClazz) {
                    return true
                }

                String methodName = actionName ?: "index"
                Method method = cClazz.getMethods().find { method -> method.name == methodName && Modifier.isPublic(method.getModifiers()) }

                AlaSecured ca = cClazz.getAnnotation(AlaSecured)
                AlaSecured ma = method.getAnnotation(AlaSecured)
                AlaSecured sa = ma ?: ca
                if (sa) {

                    boolean error = false

                    if (sa.value()) {
                        if (sa.anyRole() && sa.notRoles()) {
                            throw new IllegalArgumentException("Only one of anyRole and notRoles should be specified")
                        }

                        def roles = sa.value().toList()

                        if (sa.anyRole() && !securityPrimitives.isAnyGranted(roles)) {
                            error = true
                        } else if (sa.notRoles() && !securityPrimitives.isNotGranted(roles)) {
                            error = true
                        } else if (!securityPrimitives.isAllGranted(roles)) {
                            error = true
                        }
                    } else {
                        log.warn "No roles specified for @AlaSecured, controller ${controllerName}, action ${actionName}"
                    }

                    if (error) {
                        if (sa.message()) {
                            flash.errorMessage = sa.message()
                        }

                        if (params.returnTo) {
                            redirect(url: params.returnTo)
                        } else if (sa.statusCode() != 0) {
                            render(status: sa.statusCode())
                        } else if (sa.redirectUri()) {
                            redirect(uri: sa.redirectUri())
                        } else {
                            def redirectController =  sa.redirectController()
                            if (!redirectController) {
                                if (!ma) {
                                    log.warn('Redirecting to the current controller with a Controller level @AlaSecured, this is likely to result in a redirect loop!')
                                }
                                redirectController = controllerName
                            }
                            redirect(controller: redirectController, action: sa.redirectAction())
                        }
                        return false
                    }
                }
                return true
            }

            after = { Map model ->

            }

            afterView = { Exception e ->

            }
        }
    }
}
