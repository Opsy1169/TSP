package opsy.util;


import org.springframework.stereotype.Component;

@Component("util")
public class UtilStuff {

    public String replaceTags(String articleBody){
        articleBody = articleBody.replaceAll("<form", "&lt;form");
        articleBody = articleBody.replaceAll("<script", "&lt;script");
        articleBody = articleBody.replaceAll("</form", "&lt;/form");
        articleBody = articleBody.replaceAll("</script", "&lt;/script");
        articleBody = articleBody.replaceAll("<input", "&lt;input");
        return articleBody;
    }

    public String replaceBackToTags(String articleBody){
        articleBody = articleBody.replaceAll( "&lt;form", "<form");
        articleBody = articleBody.replaceAll( "&lt;script", "<script");
        articleBody = articleBody.replaceAll( "&lt;/form", "</form");
        articleBody = articleBody.replaceAll( "&lt;/script", "</script");
        articleBody = articleBody.replaceAll( "&lt;input", "<input");
        return articleBody;
    }
}
