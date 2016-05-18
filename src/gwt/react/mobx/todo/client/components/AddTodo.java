package gwt.react.mobx.todo.client.components;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import gwt.mobx.client.MobXReact;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.events.FormEventHandler;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.FormProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.attributeTypes.ButtonType;

import static gwt.react.client.api.React.DOM.*;

class AddTodo {
    private static InputElement input;

    public static StatelessComponent<AppStateProps, BaseContext> component = MobXReact.observer((props, context) -> {

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
}
