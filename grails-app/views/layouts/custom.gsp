<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="app.version" content="${g.meta(name:'app.version')}"/>
    <meta name="app.build" content="${g.meta(name:'app.build')}"/>
    <meta name="description" content="Atlas of Living Australia"/>
    <meta name="author" content="Atlas of Living Australia">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://www.ala.org.au/wp-content/themes/ala2011/images/favicon.ico" rel="shortcut icon"  type="image/x-icon"/>
    <title><g:layoutTitle /></title>
    <r:require modules="bootstrap, application, jqueryui"/>
    <r:layoutResources/>
    <g:layoutHead />
    <style type="text/css">
        .customizable-banner { height:200px; background-color: #000000; color:#FFF; overflow: hidden;  background-size: 100%; background-repeat: no-repeat;  background-position: 50% 50%;  background-image: url(${bannerUrl}); }
        .customizable-logo { margin-left:0px; height:100px; padding:15px 15px 15px 25px; }
        .customizable-logo-img {  max-width:300px; }
        .customizable-subbanner { background-color:rgba(0,0,0,0.5); margin-top:20px; height:50px; margin-bottom: 0;  padding:15px; }
        .customizable-subbanner-title { font-size:28px; margin-left:10px; }
        .customizable-subbanner-title  a { color:#FFF; }
        .nav-menu a { margin-right: 40px; color:#FFF; font-size:20px; }
    </style>
</head>
<body id="${pageProperty(name:'body.id')}" onload="${pageProperty(name:'body.onload')}">
<g:set var="fluidLayout" value="${pageProperty(name:'meta.fluidLayout')?:grailsApplication.config.skin?.fluidLayout}"/>

<div class="customizable-banner">
    <div class="row-fluid">
        <g:if test="${logoUrl}">
        <div class="customizable-logo pull-left">
            <img class="customizable-logo-img" src="${logoUrl}" alt="${logoAlt?:'logo'}" />
        </div>
        </g:if>
    </div>
    <div class="row-fluid">
        <div class="span12 customizable-subbanner">
            <div class="pull-left customizable-subbanner-title">
                <a href="${pageTitleLink?:'#'}" >${pageTitle}</a>
            </div>
            <g:if test="${menuLinks}">
            <nav class="nav-menu pull-right">
                <g:each in="${menuLinks}" var="menuLink">
                    <a href="${menuLink.key}" class="menulink">${menuLink.value}</a>
                </g:each>
            </nav>
            </g:if>
        </div>
    </div>
</div>

<div class="${fluidLayout?'container-fluid':'container'}" id="main-content">
    <g:layoutBody />
</div><!--/.container-->

<div class="${fluidLayout?'container-fluid':'container'} hidden-desktop">
    <%-- Borrowed from http://marcusasplund.com/optout/ --%>
    <a class="btn btn-small toggleResponsive"><i class="icon-resize-full"></i> <span>Desktop</span> version</a>
</div>

<script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<r:script>
    var pageTracker = _gat._getTracker("UA-4355440-1");
    pageTracker._initData();
    pageTracker._trackPageview();

    // show warning if using IE6
    if ($.browser && $.browser.msie && $.browser.version.slice(0,1) == '6') {
        $('#header').prepend($('<div style="text-align:center;color:red;">WARNING: This page is not compatible with IE6.' +
                ' Many functions will still work but layout and image transparency will be disrupted.</div>'));
    }
</r:script>

<!-- JS resources-->
<r:layoutResources/>

</body>
</html>
