# Spring MVC

## Url-Pattern
Servlet Url的映射匹配规则分为：精确匹配、扩展名匹配、通配符匹配以及默认处理。  
- 精确匹配：例如：`<url-pattern></url-pattern>`，`<url-pattern>/a/b/c</url-pattern>`
- 扩展名匹配：以`*.`开头，例如：`<url-pattern>*.do</url-pattern>`
- 通配符匹配：以`/*`结尾，例如：`<url-pattern>/*</url-pattern>`，`<url-pattern>/a/*</url-pattern>`
- 默认处理：`<url-pattern>/</url-pattern>`覆盖Web容器的默认处理器，例如tomcat的defaultServlet

路径匹配的顺序为：  
- 精确匹配
- 通配符匹配
- 扩展名匹配
- 默认处理

几种常见配置的区别：
- `<url-pattern></url-pattern>`：精确匹配ServletContext根路径：`http://localhost:8080/project`或`http://localhost:8080/project/`
- `<url-pattern>/</url-pattern>`：当按照优先级没有找到对应的匹配时，交给容器默认处理器处理，例如tomcat的defaultServlet，默认情况下拦截除`*.jsp`和`*.jspx`之外的所有请求
- `<url-pattern>/*</url-pattern>`：超级通配符匹配，除精确匹配外，可以匹配任何形式的请求路径

SpringMVC的RequestMapping  
SpringMVC中一般使用`BeanNameUrlHandlerMapping`或`SimpleUrlHandlerMapping`来进行路径匹配。这两个类都包含一个名为`alwaysUseFullPath`，默认值为`false`的属性。假如`DispatcherServlet`的url-pattern配置的是为带前缀的格式：`/prefix/*`，SpringMVC只会拿`*`所匹配的路径去查找对应的RequestMapping。例如`DispatcherServlet`的url-pattern为`<url-pattern>/a/*</url-pattern>`，请求的url为`/a/b/c`，那么只会匹配到`@RequestMapping("/b/c")`的处理器。  

## 视图解析器
### 多视图解析器配置
