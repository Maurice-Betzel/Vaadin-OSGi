package net.betzel.osgi.karaf.vaadin.service;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.osgi.service.component.annotations.Component;

import javax.servlet.annotation.WebServlet;

@Theme("valo")
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thank you " + name.getValue()
                    + ", Vaadin on OSGi works!"));
        });
        
        layout.addComponents(name, button);
        
        setContent(layout);
    }

    @Component(service = VaadinServlet.class)
    @WebServlet(urlPatterns = "/service/*", name = "VaadinUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = VaadinUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}