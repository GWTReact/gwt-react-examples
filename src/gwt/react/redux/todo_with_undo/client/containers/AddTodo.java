package gwt.react.redux.todo_with_undo.client.containers;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.events.FormEventHandler;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.FormProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.attributeTypes.ButtonType;
import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.redux.client.Dispatch;
import gwt.redux.client.addons.react_redux.ReactRedux;

import static gwt.react.client.api.React.DOM.*;

public class AddTodo {

    private static InputElement input;

    private static StatelessComponent<BaseProps> addTodoComp = (props) -> {
        Dispatch dispatch = props.getObj("dispatch");

        FormEventHandler handleSubmit = (event) -> {

            event.preventDefault();
            if (input.getValue().trim().length() == 0) {
                return;
            }
            dispatch.forward(Actions.addTodo(input.getValue()));
            input.setValue("");
        };

        return
            div(null,
                form(new FormProps().onSubmit(handleSubmit),
                    input(new InputProps().ref((element) -> { input = InputElement.as((Element)element); })),
                    button(new BtnProps().type(ButtonType.submit), "Add Todo")
                )
            );
    };

    public static StatelessComponent<BaseProps> component = ReactRedux.connect().toComponent(addTodoComp);
}