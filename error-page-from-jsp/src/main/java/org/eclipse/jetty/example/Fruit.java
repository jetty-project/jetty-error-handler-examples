package org.eclipse.jetty.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

public class Fruit
{
    public String getDescription(PageContext context) throws ServletException, IOException
    {
        context.forward("/naughty");
        return "(shouldn't see this)";
    }
}
