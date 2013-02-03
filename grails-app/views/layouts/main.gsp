<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="app.version" content="${g.meta(name:'app.version')}"/>
    <meta name="app.build" content="${g.meta(name:'app.build')}"/>
    <meta name="description" content="The Atlas of Living Australia's data profile"/>
    <meta name="author" content="Atlas of Living Australia">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title><g:layoutTitle /></title>

    <link rel="icon" type="image/x-icon" href="${resource(dir: 'images', file: 'favicon.ico')}">
    <link rel="shortcut icon" type="image/x-icon" href="${resource(dir: 'images', file: 'favicon.ico')}">

    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap.css')}">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}">
    <link rel="stylesheet" type="text/css" media="screen" href="${grailsApplication.config.ala.baseURL}/wp-content/themes/ala2011/css/jquery.autocomplete.css" />

    %{--<r:require module="jquery"/>--}%
    <script type="text/javascript" src="${grailsApplication.config.ala.baseURL}/wp-content/themes/ala2011/scripts/html5.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    %{--<script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${resource(dir: 'js', file: 'jquery.tools.min.js')}"></script>--}%
    <script src="${resource(dir: 'js', file: 'bootstrap.js')}"></script>

    <g:layoutHead />
    %{--<r:layoutResources/>--}%
    <script language="JavaScript" type="text/javascript" src="${grailsApplication.config.ala.baseURL}/wp-content/themes/ala2011/scripts/jquery.autocomplete.js"></script>
    <script language="JavaScript" type="text/javascript" src="${grailsApplication.config.ala.baseURL}/wp-content/themes/ala2011/scripts/uservoice.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script type="text/javascript">
        // initialise plugins
        jQuery(function(){
            // autocomplete on navbar search input
            jQuery("form#search-form-2011 input#search-2011, form#search-inpage input#search").autocomplete('http://bie.ala.org.au/search/auto.jsonp', {
                extraParams: {limit: 100},
                dataType: 'jsonp',
                parse: function(data) {
                    var rows = new Array();
                    data = data.autoCompleteList;
                    for(var i=0; i<data.length; i++){
                        rows[i] = {
                            data:data[i],
                            value: data[i].matchedNames[0],
                            result: data[i].matchedNames[0]
                        };
                    }
                    return rows;
                },
                matchSubset: false,
                formatItem: function(row, i, n) {
                    return row.matchedNames[0];
                },
                cacheLength: 10,
                minChars: 3,
                scroll: false,
                max: 10,
                selectFirst: false
            });
        });
    </script>
</head>
<body class="${pageProperty(name:'body.class')}" id="${pageProperty(name:'body.id')}" onload="${pageProperty(name:'body.onload')}">
%{--
<div class="navbar navbar-inverse navbar-static-top" id="site-header">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#"><img src="${resource(dir: 'images', file: 'logo.png')}" alt="logo"/></a>
            <div class="nav-collapse collapse">
                <form class="navbar-form pull-right" id="search-form-2011"  action="http://bie.ala.org.au/search">
                    <input type="text" name="q" class="span3" id="search-2011" placeholder="Search the Atlas">
                    <button type="submit" class="btn"><i class="icon-search"></i></button>
                </form>
                <ul class="nav pull-right">
                    <li><a href="#about">My profile</a></li>
                    <li><a href="#contact">Login</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container -->
    </div><!--/.navbar-inner -->
</div><!--/#site-header -->

<div class="navbar navbar-static-top" id="nav-site">
    <div class="navbar-inner">
        <div class="container">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Species</a></li>
                    <li><a href="#">Locations</a></li>
                    <li><a href="#">Collections</a></li>
                    <li><a href="#">Mapping &amp; analysis</a></li>
                    <li><a href="#">Data sets</a></li>
                    <li><a href="#">Blogs</a></li>
                    <li><a href="#">Get involved</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">About the Atlas <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="http://www.ala.org.au/about-the-atlas/atlas-background/">Atlas background</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/atlas-data/">Atlas data</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/our-data-providers/">Our data providers</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/how-we-integrate-data/">How we integrate data</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/downloadable-tools/">Downloadable tools</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/digitisation-guidance/">Digitisation guidance</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/communications-centre/">Communications centre</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/terms-of-use/">Terms of Use</a></li>
                            <li><a href="http://www.ala.org.au/about-the-atlas/contact-us/">Contact us</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!--/nav-collapse-->
        </div><!--/.container-->
    </div><!--/.navbar-inner-->
</div><!--/#nav-site-->
--}%

<hf:banner logoutUrl="${grailsApplication.config.grails.serverURL}/logout/logout"/>

<hf:menu/>

<div class="container" id="main-content">
    <g:layoutBody />

</div><!--/.container-->

<hf:footer/>
%{--
<footer>
    <div id="top-row">
        <div class="container">
            <div class="row-fluid">
                <div class="span2"><a href="http://www.ala.org.au/about-the-atlas/contact-us/">Contact us</a></div>
                <div class="span2"><a href="http://www.ala.org.au/about-the-atlas/atlas-background/atlas-partners/">Partners</a></div>
                <div class="span2"><a href="http://www.ala.org.au/about-the-atlas/communications-centre/">Communications</a></div>
                <div class="span2"><a href="http://www.ala.org.au/about-the-atlas/">About the Atlas</a></div>
                <div class="span2"><a href="http://www.ala.org.au/get-involved/citizen-science/">Citizen Science</a></div>
                <div class="span2"><a href="http://www.ala.org.au/help/">Help</a></div>
            </div><!--/.row-fluid-->
        </div><!--/.container-->
    </div><!--/#top-row-->
    <div id="row-2">
        <div class="container">
            <div class="row-fluid">
                <div class="span2">
                    <div class="columnTitle">Map &amp; analyse</div>
                    <div><a href="http://regions.ala.org.au/">Species by region</a></div>
                    <div><a href="http://biocache.ala.org.au/explore/your-area">Species in your area</a></div>
                    <div><a href="http://spatial.ala.org.au/">Species distributions</a></div>
                    <div><a href="http://www.ala.org.au/faq/spatial-portal/">Spatial case studies</a></div>
                </div>
                <div class="span2">
                    <div class="columnTitle">Download</div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/downloadable-tools/open-source-software/">Open source software</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/downloadable-tools/field-data-capture-toolkit/">Field Data Capture toolkit</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/downloadable-tools/web-services/">Web services</a></div>
                </div>
                <div class="span2">
                    <div class="columnTitle">Share</div>
                    <div><a href="http://volunteer.ala.org.au/">Volunteer for online projects</a></div>
                    <div><a href="http://sightings.ala.org.au/">Record a sighting</a></div>
                    <div><a href="http://sightings.ala.org.au/recent">Recent sightings</a></div>
                    <div><a href="http://sightings.ala.org.au/mine">My sightings</a></div>
                    <div><a href="http://www.ala.org.au/get-involved/">Ways to get involved</a></div>
                    <div><a href="">Upload data sets</a></div>
                    <div><a href="http://www.ala.org.au/get-involved/">Upload media</a></div>
                    <div><a href="http://www.ala.org.au/faq/data-licensing/">Terms &amp; conditions of sharing</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/terms-of-use/privacy-policy/">Privacy</a></div>
                </div>
                <div class="span2">
                    <div class="columnTitle">Data</div>
                    <div><a href="http://biocache.ala.org.au">Find a record</a></div>
                    <div><a href="http://collections.ala.org.au/datasets">Find a data set</a></div>
                    <div><a href="http://www.ala.org.au/faq/data-sensitivity/">Sensitive data</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/how-we-integrate-data/">Data integration</a></div>
                    <div><a href="http://dashboard.ala.org.au" >Dashboard</a></div>
                    <div><a href="http://sandbox.ala.org.au/">Sandbox</a></div>
                    <div><a href="http://lists.ala.org.au/">Upload species lists</a></div>
                </div>
                <div class="span2">
                    <div class="columnTitle">Publications</div>
                    <div><a href="http://www.ala.org.au/faq/">FAQ</a></div>
                    <div><a href="http://www.ala.org.au/faq/citizen-science/field-data-capture-toolkit-help/">Field Data Capture Toolkit help</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/communications-centre/">Communications</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/atlas-background/atlas-governance/">Atlas governance</a></div>
                    <div><a href="http://www.ala.org.au/about-the-atlas/digitisation-guidance/">Digitisation guidance</a></div>
                </div>
                <div class="span2">
                    <div class="columnTitle">Associated sites</div>
                    <div><a href="https://m.ala.org.au/">Atlas mobile site</a></div>
                    <div><a href="http://bhl.ala.org.au/">Biodiversity Heritage Library</a></div>
                    <div><a href="http://www.identifylife.org/">IdentifyLife</a></div>
                    <div><a href="http://morphbank.ala.org.au/">Morphbank  images</a></div>
                    <div><a href="http://www.ozcam.org.au/">OZCAM</a></div>
                    <div><a href="http://www.chah.gov.au/avh/">Australia's Virtual Herbarium</a></div>
                    <div><a href="http://bold.ala.org.au/">BOLD</a></div>
                    <div><a href="http://biodiversity.org.au/confluence/display/bdv/NSL%2bServices">National Species Lists</a></div>
                    <div><a href="http://www.taxonomy.org.au">TRIN Biodiversity Information</a></div>
                </div>
            </div><!--/.row-fluid-->
        </div><!--/.container-->
    </div><!--/#row-2-->
    <div id="row-3">
        <div class="container">
            <div class="row-fluid">
                <div class="span9">
                    <a href="http://www.gbif.org/"><img src="http://www.ala.org.au/wp-content/themes/ala2011/images/GBIF.png" width="52" height="50" alt="GBIF logo" /></a> The Atlas of Living Australia is the Australian node of the <a href="http://www.gbif.org/" class="external">Global Biodiversity Information Facility (GBIF)</a>.
                </div>
                <div class="span3">
                    <img src="http://www.ala.org.au/wp-content/themes/ala2011/images/agi-s.png" width="167" height="46" alt="Atlas of Living Australia, an Australian Government Initiative" />
                </div>
            </div><!--/.row-fluid-->
            <div class="row-fluid">
                <div class="span12">
                    <a href="http://creativecommons.org/licenses/by/3.0/au/" title="External link to Creative Commons"><img src="http://www.ala.org.au/wp-content/themes/ala2011/images/creativecommons.png" width="88" height="31" alt="" /></a>
                    This site is licensed under a <a href="http://creativecommons.org/licenses/by/3.0/au/" title="External link to Creative Commons" class="external">Creative Commons Attribution 3.0 Australia License</a>. Provider content may be covered by other <a href="http://www.ala.org.au/about-the-atlas/terms-of-use/" title="Terms of Use">Terms of Use</a>.
                </div>
            </div><!--/.row-fluid-->
        </div><!--/.container-->
    </div><!--/#row-3-->
</footer>
--}%

%{--<r:layoutResources/>--}%

<script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
    var pageTracker = _gat._getTracker("UA-4355440-1");
    pageTracker._initData();
    pageTracker._trackPageview();
</script>
<script type="text/javascript">
    // show warning if using IE6
    if ($.browser.msie && $.browser.version.slice(0,1) == '6') {
        $('#header').prepend($('<div style="text-align:center;color:red;">WARNING: This page is not compatible with IE6.' +
                ' Many functions will still work but layout and image transparency will be disrupted.</div>'));
    }
</script>
</body>
</html>