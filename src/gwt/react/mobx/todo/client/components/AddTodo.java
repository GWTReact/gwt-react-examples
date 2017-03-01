package gwt.react.mobx.todo.client.components;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.FormEventHandler;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.FormProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.attributeTypes.ButtonType;

import static gwt.react.client.api.React.DOM.*;

class AddTodo {
    private static InputElement input;

    private static StatelessComponent<AppStateProps> component = MobXReact.observer((props) -> {

        FormEventHandler handleSubmit = (event) -> {

            event.preventDefault();
            if (input.getValue().trim().length() == 0) {
                return;
            }

            props.appState.addTodo(input.getValue());
            input.setValue("");
        };

        return
            div(null,
                form(new FormProps().onSubmit(handleSubmit),
                    input(new InputProps().ref((element) -> { input = InputElement.as((Element)element); })),
                    button(new BtnProps().type(ButtonType.submit), "Add Todo")
                )
            );
    });

    static ReactElement<AppStateProps, ?> addTodo(AppStateProps props) {
        return React.createElement(component, props);
    }
}
