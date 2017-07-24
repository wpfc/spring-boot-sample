package cn.edu.ntu.config;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.filter.stat.StatFilter;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
initParams={
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// ºöÂÔ×ÊÔ´
})
public class DruidStatFilter extends StatFilter {

}
