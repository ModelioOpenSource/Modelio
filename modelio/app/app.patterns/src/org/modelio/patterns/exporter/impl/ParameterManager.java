/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.patterns.exporter.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.model.ProfileUtils.ParameterModelData;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerTagTypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("e2906bfc-2e08-41f5-89bb-157c43f771a7")
class ParameterManager {
    @objid ("14a2afce-f19f-4c78-9c70-46dd76061634")
    private static final String START_MARKER = "$(";

    @objid ("862a1baf-af86-44b1-bc4b-a2ca66c867cf")
    private static final char END_MARKER = ')';

    @objid ("0e07ed12-f779-4059-a02b-05aa25cf8206")
    private static final char SEPARATOR_MARKER = ',';

    @objid ("f7271d0c-492f-46a8-b14d-c2f89e36f6f8")
    private static final String UPPER = "upper";

    @objid ("8ed19875-f0e7-40ed-ae1a-79e2a8fe8a84")
    private static final String LOWER = "lower";

    @objid ("fc427ace-487a-434e-bcb8-3823a12b89d8")
    private static final String FIRST = "cfirst";

    @objid ("7a09b984-7814-4502-8fc0-67e07e667cbc")
    private static final String LOWERFIRST = "lfirst";

    @objid ("2361fc6e-cbc8-484d-969a-ac38604f6a04")
    private PatternModelAnalysis report;

    @objid ("6b806399-debd-4df6-9cc1-550474f65c84")
    public ParameterManager(PatternModelAnalysis report) {
        this.report = report;
    }

    @objid ("c66e9564-e53f-4fd5-ba07-067ce301cd72")
    @SuppressWarnings("deprecation")
    public String parameterFormater(ModelElement element) {
        this.report.addElementParameter(element);
        
        // Look for corresponding parameter name, in case of renaming...
        ParameterModelData parameterData = ProfileUtils.getParameterData(element.getUuid().toString(), element);
        String name = element.getName();
        if (parameterData != null) {
            name = parameterData.name;
        } else {
            name = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_NAME);
        }
        
        if (name == null || name.isEmpty()) {
            name = element.getName();
        }
        return "(MObject) this.parameters.get(\"" + name + "\")";
    }

    @objid ("1f25e551-9a73-4f96-a8c3-853a9f4514c2")
    public String parameterFormater(String initialValue, MObject element) {
        String value = escape(initialValue);
        String resultValue = "";
        
        int valueIdx = 0;
        int startIdx = value.indexOf(ParameterManager.START_MARKER, 0);
        
        while (startIdx != -1) {
            int endIdx = startIdx;
            String parameterName = "";
            StringBuilder modeSB = new StringBuilder();
        
            for (endIdx = startIdx; endIdx < value.length(); endIdx++) {
                if (value.charAt(endIdx) == ParameterManager.SEPARATOR_MARKER) {
                    for (endIdx = endIdx + 1; endIdx < value.length(); endIdx++) {
                        if (value.charAt(endIdx) == ParameterManager.END_MARKER) {
                            break;
                        } else {
                            modeSB.append(value.charAt(endIdx));
                        }
                    }
                    parameterName = parameterName + ")";
                    break;
                } else if (value.charAt(endIdx) == ParameterManager.END_MARKER) {
                    parameterName = parameterName + ")";
                    break;
                } else {
                    parameterName = parameterName + value.charAt(endIdx);
                }
            }
        
            if (endIdx > startIdx) {
                this.report.addStringParameter(parameterName, new MRef(element));
        
                resultValue = resultValue + value.substring(valueIdx, startIdx);
        
                String mode = modeSB.toString();
                if (mode.equals(ParameterManager.UPPER)) {
                    resultValue = resultValue + "\" + ((String) this.parameters.get(\"" + parameterName + "\")).toUpperCase()+\"";
                } else if (mode.equals(ParameterManager.LOWER)) {
                    resultValue = resultValue + "\" + ((String) this.parameters.get(\"" + parameterName + "\")).toLowerCase()+\"";
                } else if (mode.equals(ParameterManager.FIRST)) {
                    resultValue = resultValue + "\" + ((String) this.parameters.get(\"" + parameterName
                            + "\")).substring(0, 1).toUpperCase() + ((String) this.parameters.get(\"" + parameterName
                            + "\")).substring(1, ((String) this.parameters.get(\"" + parameterName + "\")).length())  +\"";
                } else if (mode.equals(ParameterManager.LOWERFIRST)) {
                    resultValue = resultValue + "\" + ((String) this.parameters.get(\"" + parameterName
                            + "\")).substring(0, 1).toLowerCase() + ((String) this.parameters.get(\"" + parameterName
                            + "\")).substring(1, ((String) this.parameters.get(\"" + parameterName + "\")).length())  +\"";
                } else {
                    resultValue = resultValue + "\" + (String) this.parameters.get(\"" + parameterName + "\")+\"";
                }
        
            }
            valueIdx = endIdx + 1;
            startIdx = value.indexOf(ParameterManager.START_MARKER, valueIdx);
        }
        
        if (valueIdx < value.length()) {
            resultValue = resultValue + value.substring(valueIdx, value.length()) + "\" +\"";
        }
        return resultValue;
    }

    @objid ("c363f1cb-ea88-4e9e-b6f8-10b5a59a0d97")
    private String escape(String value) {
        // Replace character constants to avoid error in the generated file
        String result = value;
        result = result.replace("\n", "\\n");
        result = result.replace("\r", "\\r");
        result = result.replace("\f", "\\f");
        result = result.replace("\t", "\\t");
        result = result.replace("\b", "\\b");
        result = result.replace("\"", "\\\"");
        result = result.replace("\\:", "\\\\:");
        return result;
    }

}
