package cn.edu.just.demo.service.impl;

import cn.edu.just.demo.dao.PropertyMapper;
import cn.edu.just.demo.model.Property;
import cn.edu.just.demo.service.PropertyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;



    @Override
    public String getProperty(HttpServletRequest request) {
        String callback = request.getParameter("callback");
        List<Property> list=propertyMapper.selectAll();
        JSONArray json = new JSONArray();
        for(Property date_property : list){
            //System.out.println("getProperty:"+date_property.getDate()+date_property.getProperty());
            JSONObject jo = new JSONObject();
            try {
                jo.put("date", date_property.getDate());
                jo.put("property", date_property.getProperty());
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.add(jo);
        }
        String result=callback+"("+json.toString()+")";
        //System.out.println("getProperty:"+result);
        return result;
    }
}
