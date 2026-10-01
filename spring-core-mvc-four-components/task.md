## Demo Project — `springmvc-four-components`

One `DispatcherServlet`, one XML file, every seam visible at once.

No `@EnableWebMvc`, no `<mvc:annotation-driven/>`, no component scan, no interceptors. Every strategy object is a bean you can point at in `/WEB-INF/dispatcher-servlet.xml` — **that file is the deliverable**; the Java and the JSPs are deliberately as thin as they can be.

**Three mappings** competing in one ordered chain · **four adapters**, one of them ours · **six handler shapes** · **two view resolvers** chained by order.

```
mvn clean package cargo:run
→ http://localhost:8080/fourcomp/
```

---

## The Routes, And What Each One Isolates

| URL            | Handler shape                   | Mapping                            | Adapter                            | View step                                    |
| -------------- | ------------------------------- | ---------------------------------- | ---------------------------------- | -------------------------------------------- |
| `/diagnostics` | `AbstractController`            | `SimpleUrlHandlerMapping`          | `SimpleControllerHandlerAdapter`   | JSP                                          |
| `/home`        | `AbstractController`            | `SimpleUrlHandlerMapping`          | `SimpleControllerHandlerAdapter`   | JSP                                          |
| `/about`       | `ParameterizableViewController` | `SimpleUrlHandlerMapping`          | `SimpleControllerHandlerAdapter`   | JSP                                          |
| `/legacy`      | `Controller` interface          | **`BeanNameUrlHandlerMapping`**    | `SimpleControllerHandlerAdapter`   | JSP                                          |
| `/annotated`   | `@Controller`                   | **`RequestMappingHandlerMapping`** | **`RequestMappingHandlerAdapter`** | JSP                                          |
| `/raw`         | `HttpRequestHandler`            | `SimpleUrlHandlerMapping`          | **`HttpRequestHandlerAdapter`**    | **none**                                     |
| `/greet`       | **our own type**                | `SimpleUrlHandlerMapping`          | **our own adapter**                | JSP                                          |
| `/plain`       | `AbstractController`            | `SimpleUrlHandlerMapping`          | `SimpleControllerHandlerAdapter`   | **`BeanNameViewResolver` → `PlainTextView`** |
| `/go-home`     | `ParameterizableViewController` | `SimpleUrlHandlerMapping`          | `SimpleControllerHandlerAdapter`   | **`RedirectView` → 302**                     |

Each row changes **one** thing against the row above it. Read them in order.

---

## Session 1 Recap

- **MVC** is a pattern; **Spring MVC** is Spring's implementation of it, built on servlets
- **`DispatcherServlet`** is the Front Controller — one entry point, delegates everything
- **Root context** (services) is the **parent** of the **Web context** (controllers); child sees parent, not vice versa
- **`HandlerMapping`** = _which_ handler (+ interceptors, as a `HandlerExecutionChain`)
- **`HandlerAdapter`** = _how_ to invoke that handler — the **Adapter pattern**, because handlers come in several shapes
- **Controllers**: `@Controller` (modern), `Controller`/`AbstractController` (legacy), `HttpRequestHandler` — three shapes, three adapters
- **Model / ModelMap / ModelAndView** carry data to the view
- **`ViewResolver`** = logical name → real `View`
- If you define no beans, `DispatcherServlet.properties` supplies **defaults**; `@EnableWebMvc` upgrades them