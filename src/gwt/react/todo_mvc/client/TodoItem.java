package gwt.react.todo_mvc.client;

import com.google.gwt.dom.client.InputElement;
import gwt.interop.utils.shared.functional.JsBiConsumer;
import gwt.interop.utils.client.plainobjects.JsPlainObj;
import gwt.react.client.api.React;
import gwt.react.client.components.ReactClassSpec;
import gwt.react.client.components.ReactClass;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.events.*;
import gwt.react.client.proptypes.*;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.proptypes.html.HtmlProps;
import gwt.react.client.proptypes.html.InputProps;
import gwt.react.client.proptypes.html.LabelProps;
import gwt.react.client.proptypes.html.attributeTypes.InputType;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.interop.utils.client.plainobjects.JsPlainObj.$;
import static gwt.react.client.api.React.DOM.*;

@JsType
class TodoItem extends ReactClassSpec<TodoItem.TodoItemProps, TodoItem.TodoState> {

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    static class TodoItemProps extends BaseProps {
        TodoModel.Todo todo;
        boolean isEditing;
        JsBiConsumer<TodoModel.Todo, String> doSave;
        JsBiConsumer<TodoList.Action, TodoModel.Todo> doAction;
    }

    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    static class TodoState extends JsPlainObj {
        String editText;
    }

    private TodoState newTodoItemState(String editText) {
        return $(new TodoState(), "editText", editText);
    }

    private void submitTodo(FocusEvent event) {
        String val = getState().editText;
        if (val != null && !val.isEmpty()) {
            getProps().doSave.accept(getProps().todo, val);

            setState(newTodoItemState(val));
        } else {
            getProps().doAction.accept(TodoList.Action.DESTROY, getProps().todo);
        }
    }

    private void handleEdit(MouseEvent event) {
        getProps().doAction.accept(TodoList.Action.EDIT, getProps().todo);
        setState(newTodoItemState(getProps().todo.title));
    }

    private void handleKeyDown(KeyboardEvent event) {
        if (event.which == App.ESCAPE_KEY) {
            setState(newTodoItemState(getProps().todo.title));
            getProps().doAction.accept(TodoList.Action.CANCEL, getProps().todo);
        } else if (event.which == App.ENTER_KEY) {
            submitTodo(null);
        }
    }

    private void handleChange(FormEvent event) {
        if (getProps().isEditing) {
            setState(newTodoItemState(InputElement.as(event.target).getValue()));
        }
    }

    public TodoState getInitialState() {
        return newTodoItemState(getProps().todo.title);
    }

    /**
     * This is a completely optional performance enhancement that you can
     * implement on any React component. If you were to delete this method
     * the app would still work correctly (and still be very performant!), we
     * just use it as an example of how little code it takes to get an order
     * of magnitude performance improvement.
     */
    @SuppressWarnings("unused")
    public boolean shouldComponentUpdate(TodoItemProps nextProps, TodoState nextState) {
        return (nextProps.todo != getProps().todo ||
                nextProps.isEditing != getProps().isEditing ||
                !nextState.editText.equals(getState().editText));
    }

    /**
     * Safely manipulate the DOM after updating the state when invoking
     * `getProps().onEdit()` in the `handleEdit` method above.
     * For more info refer to notes at https://facebook.github.io/react/docs/component-api.html#setstate
     * and https://facebook.github.io/react/docs/component-specs.html#updating-componentdidupdate
     */
    @SuppressWarnings("unused")
    public void componentDidUpdate(TodoItemProps prevProps, TodoItemProps prevState) {

        if (!prevProps.isEditing && getProps().isEditing) {
            InputElement inputEl = InputElement.as(getRef("editField"));
            inputEl.focus();
            inputEl.select();
        }
    }

    public ReactElement render() {
        TodoItemProps props = getProps();

        return
                li(new HtmlProps().className(
                        Classnames.get("completed", props.todo.completed, "editing", props.isEditing)),
                        div(new HtmlProps().className("view"),
                                input(new InputProps().className("toggle").type(InputType.checkbox).checked(props.todo.completed)
                                        .onChange((event) -> props.doAction.accept(TodoList.Action.TOGGLE, getProps().todo))),
                                label(new LabelProps().OnDoubleClick(this::handleEdit), props.todo.title),
                                button(new BtnProps().className("destroy").onClick((event) -> props.doAction.accept(TodoList.Action.DESTROY, getProps().todo)))
                        ),
                        input(new InputProps().ref("editField")
                                .className("edit")
                                .defaultValue(getState().editText)
                                .onBlur(this::submitTodo)
                                .onChange(this::handleChange)
                                .onKeyDown(this::handleKeyDown))
                );
    }

    static ReactClass<TodoItemProps> component = React.createClass(new TodoItem());
}
