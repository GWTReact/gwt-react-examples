package gwt.react.widget_interop.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.react.client.api.React;
import gwt.react.client.components.ReactClass;


public class App implements EntryPoint {
    private PopupPanel dialog;
    private ReactClass<StatefulReactClassSpec.Props> statefulComponent = React.createClass(new StatefulReactClassSpec());

    @Override
    public void onModuleLoad() {
        final Button button = new Button("Show embedded React view");

        button.addClickHandler((event) -> {
            //Show a React component in a popup panel
            dialog = new PopupPanel(true);

            StatefulReactClassSpec.Props statefulComp1Props = new StatefulReactClassSpec.Props();
            statefulComp1Props.aProp = "Embedded React component 1";

            ReactPanel reactPanel = new ReactPanel(React.createElement(statefulComponent, statefulComp1Props));
            reactPanel.setWidth("700px");
            reactPanel.setHeight("700px");

            dialog.add(reactPanel);
            dialog.setGlassEnabled(true);
            dialog.center();
        });

        RootPanel.get("replaceme").add(button);

        //Add React component to Root Panel
        StatefulReactClassSpec.Props statefulComp2Props = new StatefulReactClassSpec.Props();
        statefulComp2Props.aProp = "Embedded React component 2";

        ReactPanel reactPanel = new ReactPanel(React.createElement(statefulComponent, statefulComp2Props));
        RootPanel.get("replaceme2").add(reactPanel);
    }
}