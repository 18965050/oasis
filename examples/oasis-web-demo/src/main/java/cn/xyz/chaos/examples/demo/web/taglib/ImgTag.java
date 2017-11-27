package cn.xyz.chaos.examples.demo.web.taglib;

import java.io.File;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import cn.xyz.chaos.common.utils.SpringContextUtils;
import cn.xyz.chaos.examples.demo.provider.ProductProvider;

public class ImgTag extends TagSupport {

    private String type;

    private String id;

    private String cssClass;

    private String alt;

    private String height = "";

    private String width = "";

    private JspWriter out;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public int doStartTag() throws JspException {
        this.out = pageContext.getOut();
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        String webRootPath = this.pageContext.getServletContext().getRealPath("/");
        if (!webRootPath.endsWith(File.separator)) {
            webRootPath += File.separator;
        }
        String fileName = webRootPath + "cache" + File.separator + this.type + File.separator + this.id + ".jpg";
        File file = new File(fileName);
        if (!file.exists()) {
            if (this.getType().equalsIgnoreCase("product")) {
                ProductProvider productProvider = SpringContextUtils.getBean(ProductProvider.class);
                int prodId = Integer.valueOf(this.getId().split("-")[1]);
                productProvider.get(prodId);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<img alt=\"" + this.getAlt() + "\" class=\"" + this.getCssClass() + "\" ");
        if (StringUtils.isNotBlank(this.getWidth())) {
            sb.append("width=\"" + this.getWidth() + "\" ");
        }
        if (StringUtils.isNotBlank(this.getHeight())) {
            sb.append("height=\"" + this.getHeight() + "\" ");
        }
        sb.append("src=\"/cache/" + this.getType() + "/" + this.getId() + ".jpg\" />");
        try {
            this.out.write(sb.toString());
        } catch (IOException e) {
            // ignore
        }
        return super.doEndTag();
    }
}
