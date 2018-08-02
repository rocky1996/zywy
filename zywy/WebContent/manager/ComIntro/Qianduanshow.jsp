<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/detail.css">
    <script src="${pageContext.request.contextPath }/js/jquery-3.0.0.min.js"></script>
   
    <script src="https://cdn.bootcss.com/highlight.js/9.12.0/highlight.min.js"></script>
    <script src="https://cdn.bootcss.com/markdown-it/8.4.0/markdown-it.js"></script>
    <style>
    	* {
			margin: 0 auto;    	
    	}
        .topic_content .markdown-text p {
            text-indent: 2em;
            font-size: 1rem;
        }
        img {
        	width: 350px;
        	height: 400px;
        }
    </style>
    </style>
</head>

<body>

<div id="main" style="display: inline-block;">
        <div id="content">
            <div class="panel">
                <div>
                    <div style="margin-top: 10px;display: inline-block;">
                        <a href="${pageContext.request.contextPath }/index.jsp" style="font-size: 25px;background-color: #7aba7b;color: white;padding: 5px;font-weight:200;"> &lt; 返回 </a>
                    </div>
 				<c:if test="${!empty ci}">
                    <span class="topic_full_title">
                    	 ${ci.time }
                		 ${ci.title }
                	</span>
                </div>
                <div class="header topic_header" style="display: inline-block;">
                    <div class="author_content">
                        <div class="topic_content">
                            <div class="markdown-text" style="padding: 2rem;">${ci.control }</div>
               </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script defer="defer">
        window.md = window.markdownit({
        	highlight: function (str, lang) {
        		if (lang && hljs.getLanguage(lang)) {
        			try {
        				return hljs.highlight(lang, str).value;
        			} catch (__) {}
        		}

        		return ''; // use external default escaping
        	}
        })
       content = $('.markdown-text')[0].innerHTML
       $('.markdown-text')[0].innerHTML = md.render(content)
    </script>
</body>
</html>