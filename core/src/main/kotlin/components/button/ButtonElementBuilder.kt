package components.button

import components.button.enums.ButtonColor
import components.button.enums.ButtonSize
import components.button.enums.ButtonVariant
import components.buttonbase.ButtonBaseElementBuilder
import kotlinx.html.Tag
import react.RComponent
import react.RProps
import react.RState
import kotlin.reflect.KClass

class ButtonElementBuilder<T: Tag> internal constructor(
    type: RComponent<RProps, RState>,
    tag: KClass<T>
) : ButtonBaseElementBuilder<T>(type, tag), ButtonAttributes {

    override var color: ButtonColor
        get() = ButtonColor.valueOf(@Suppress("UnsafeCastFromDynamic") props.asDynamic()["color"])
        set(value) { setProp("color", value.toString()) }
    override var fullWidth: Boolean
        get() = @Suppress("UnsafeCastFromDynamic") props.asDynamic()["fullWidth"]
        set(value) { setProp("fullWidth", value) }
    override var href: String
        get() = @Suppress("UnsafeCastFromDynamic") props.asDynamic()["href"]
        set(value) { setProp("href", value) }
    override var mini: Boolean
        get() = @Suppress("UnsafeCastFromDynamic") props.asDynamic()["mini"]
        set(value) { setProp("mini", value) }
    override var size: ButtonSize
        get() = ButtonSize.valueOf(@Suppress("UnsafeCastFromDynamic") props.asDynamic()["size"])
        set(value) { setProp("size", value.toString()) }
    override var variant: ButtonVariant
        get() = ButtonVariant.valueOf(@Suppress("UnsafeCastFromDynamic") props.asDynamic()["variant"])
        set(value) { setProp("variant", value.toString()) }
}