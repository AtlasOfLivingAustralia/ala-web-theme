class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"/testAuth"(view:"/testAuth")
		"500"(view:'/error')
	}
}
