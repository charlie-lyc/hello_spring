package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /**
     * 리턴 대상이 어떤 것 이냐에 따라 다양한 방식
     */
    // 1. 정적 컨텐츠 : HTML page  ex) /hello-static.html

    // 2. 템플릿 엔진 : data, template
    @GetMapping("hello") // <- route : /hello
    public String hello(Model model) {
        model.addAttribute("data", "Spring");
        return "hello"; // <- template(view name) : templates/hello.html
    }

    // 3. MVC : request, data, template
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name,  Model model) {
        model.addAttribute("name", name);
        return "hello-template"; // <- template(view name) : templates/hello-template.html
    }

    // 4. 문자열 : request, response, string
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name, Model model) {
        return "hello " + name; // <- value : string type
    }

    // 5. API : request, response, object(JSON)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // <- object : JSON
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
