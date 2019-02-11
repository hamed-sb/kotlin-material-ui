package demo.components

import components.formcontrol.formControl
import components.input.input
import components.inputadornment.InputAdornmentElementBuilder
import components.inputadornment.enums.InputAdornmentPosition
import components.inputbase.values.InputValue
import components.inputlabel.inputLabel
import components.menuitem.menuItem
import components.textfield.textField
import components.textfield.values.TextFieldValue
import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.h2
import styled.css
import styled.styledDiv
import styles.childWithStyles

val ranges: List<Pair<String, String>>
    get() = listOf(
        "0-20" to "0 to 20",
        "21-50" to "21 to 50",
        "51-100" to "51 to 100"
    )

class InputAdornmentsDemo : RComponent<RProps, InputAdornmentsState>() {
    override fun InputAdornmentsState.init() {
        amount = ""
        password = ""
        weight = ""
        weightRange = ""
        showPassword = false
    }

    fun handleOnChange(prop: String): (Event) -> Unit = { event ->
        val value = event.target.asDynamic().value
        setState { asDynamic()[prop] = value }
    }

    fun handleClickShowPassword(): () -> Unit = {
        setState { showPassword = !state.showPassword  }
    }

    override fun RBuilder.render() {
        val rootStyle = props.asDynamic()["classes"]["root"] as String
        val marginStyle = props.asDynamic()["classes"]["margin"] as String
        val textFieldStyle = props.asDynamic()["classes"]["textField"] as String

        div {
            h2 {
                +"Input Adornments"
            }
        }

        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.center
                backgroundColor = Color("#eeeeee")
                boxSizing = BoxSizing.inherit
                padding(24.px)
            }

            div(classes = rootStyle) {
                textField {
                    attrs {
                        id = "simple-start-adornment"
                        label = "With normal TextField"
                        rootClass("$marginStyle $textFieldStyle")
                        InputProps {
                            attrs {
                                startAdornment(startAdornment("Kg"))
                            }
                        }
                    }

                }

                textField {
                    attrs {
                        select = true
                        label = "With Select"
                        rootClass("$marginStyle $textFieldStyle")
                        InputProps {
                            attrs {
                                startAdornment(startAdornment("Kg"))
                            }
                        }
                        value = TextFieldValue.String(state.weightRange)
                        onChangeFunction = handleOnChange("weightRange")
                    }
                    ranges.forEach {
                        menuItem {
                            attrs {
                                key = it.first
                                setProp("value", it.second)
                            }
                            +it.first
                        }
                    }
                }

                formControl {
                    attrs {
                        fullWidth = true
                        rootClass(marginStyle)
                    }
                    inputLabel {
                        attrs {
                            this["htmlFor"] = "adornment-amount"
                        }
                        +"Amount"
                    }
                    input {
                        attrs {
                            id = "adornment-amount"
                            value = InputValue.String(state.amount)
                            onChangeFunction = handleOnChange("amount")
                            startAdornment(startAdornment("$"))
                        }
                    }
                }
            }
        }
    }

    private fun startAdornment(unit: String): InputAdornmentElementBuilder<DIV>.() -> Unit = {
            attrs {
                position = InputAdornmentPosition.start
            }
            +unit
        }

    companion object {
        fun render(rBuilder: RBuilder) = rBuilder.childWithStyles(
            InputAdornmentsDemo::class,
            "root" to root,
            "margin" to margin,
            "withoutLabel" to withoutLabel,
            "textField" to textField
        ) { }

        private val root = CSSBuilder().apply {
            display = Display.flex
            flexWrap = FlexWrap.wrap
            boxSizing = BoxSizing.inherit
        }

        private val margin = CSSBuilder().apply {
            margin(8.px)
        }

        private val withoutLabel = CSSBuilder().apply {
            marginTop = 24.px
        }

        private val textField = CSSBuilder().apply {
            flexBasis = 200.px.basis
        }
    }
}

interface InputAdornmentsState : RState {
    var amount: String
    var password: String
    var weight: String
    var weightRange: String
    var showPassword: Boolean
}
