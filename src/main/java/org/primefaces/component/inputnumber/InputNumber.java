/*
 * Copyright 2009-2018 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.component.inputnumber;

import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.component.UINamingContainer;
import javax.el.ValueExpression;
import javax.el.MethodExpression;
import javax.faces.render.Renderer;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import java.util.List;
import java.util.ArrayList;
import org.primefaces.util.ComponentUtils;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@ResourceDependencies({
	@ResourceDependency(library="primefaces", name="components.css"),
	@ResourceDependency(library="primefaces", name="jquery/jquery.js"),
	@ResourceDependency(library="primefaces", name="core.js"),
	@ResourceDependency(library="primefaces", name="components.js"),
	@ResourceDependency(library="primefaces", name="inputnumber/inputnumber.js"),
	@ResourceDependency(library="primefaces", name="inputnumber/inputnumber.css")
})
public class InputNumber extends InputNumberBase implements org.primefaces.component.api.Widget,org.primefaces.component.api.InputHolder {



    public final static String STYLE_CLASS = "ui-inputnumber ui-widget";

    @Override
	public String getInputClientId() {
		return getClientId() + "_input";
	}

    @Override
    public String getValidatableInputClientId() {
        return getClientId() + "_hinput";
    }

    @Override
    public void setLabelledBy(String labelledBy) {
        getStateHelper().put("labelledby", labelledBy);
    }

    @Override
    public String getLabelledBy() {
        return (String) getStateHelper().get("labelledby");
    } 

    public String getDecimalSeparator() {
        return (String) getStateHelper().eval("decimalSeparator", getCalculatedDecimalSepartor());
    }

    public void setDecimalSeparator(final String decimalSeparator) {
        getStateHelper().put("decimalSeparator", decimalSeparator);
    }

    public String getThousandSeparator() {
        return (String) getStateHelper().eval("thousandSeparator", getCalculatedThousandSeparator());
    }

    public void setThousandSeparator(final String thousandSeparator) {
        getStateHelper().put("thousandSeparator", thousandSeparator);
    }

    private String getCalculatedDecimalSepartor(){
        String decimalSeparator = (String) getStateHelper().eval("decimalSeparator", null);
        if (decimalSeparator==null){
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
            decimalSeparator = Character.toString(decimalFormatSymbols.getDecimalSeparator());
        }
        return decimalSeparator;
    }

    private String getCalculatedThousandSeparator(){
        String thousandSeparator = (String) getStateHelper().eval("thousandSeparator", null);
        if (thousandSeparator==null){
            Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
            thousandSeparator =  Character.toString(decimalFormatSymbols.getGroupingSeparator());
        }
        return thousandSeparator;
    }

}