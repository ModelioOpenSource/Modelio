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

package org.modelio.patterns.apply.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.ConstantParameter;
import org.modelio.patterns.model.information.ElementParameter;
import org.modelio.patterns.model.information.Parameter;
import org.modelio.patterns.model.information.StringParameter;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("7ed46c03-2d11-427b-ae18-bc0b59be0281")
public class ApplyPatternData {
    @objid ("3318b086-353c-467f-a2a8-ec161ef248e6")
    private RuntimePattern pattern;

    @objid ("aba0a4c1-dca7-44a0-8306-077ef54d9872")
    private Map<String, Object> parameterValues;

    @objid ("538d8e41-94b6-4764-a516-875cbb5dce80")
    private ICoreSession session;

    @objid ("d4ede5e0-a4e4-4358-9b05-7e6d9e9c72bf")
    private IModelioPickingService pickingService;

    @objid ("97c45619-1005-42e8-9c3f-6ae9e1502595")
    public ApplyPatternData(RuntimePattern pattern, ModelElement selectedElement, ICoreSession session, IModelioPickingService pickingService) {
        this.pattern = pattern;
        this.session = session;
        this.pickingService = pickingService;
        
        this.parameterValues = getInitialParameterValues(pattern, selectedElement);
    }

    /**
     * Get initial parameter values from a pattern.
     * 
     * @param selectedElement currently selected element, used as first root.
     */
    @objid ("39d63261-68dd-42c0-b6f5-03a85ef51549")
    private Map<String, Object> getInitialParameterValues(RuntimePattern pattern, ModelElement selectedElement) {
        MMetamodel metamodel = this.session.getMetamodel();
        
        HashMap<String, Object> values = new HashMap<>();
        
        List<Parameter> ownerList = pattern.getPatternOwner();
        if (ownerList.size() == 1) {
            MObject owner = selectedElement;
            if (owner != null && owner.getMClass().getQualifiedName().equals(ownerList.get(0).getMetaclass())) {
                values.put(ownerList.get(0).getName(), owner);
            }
        }
        
        List<Parameter> parameters = pattern.getParameters();
        for (Parameter param : parameters) {
            if (param instanceof StringParameter) {
                values.put(param.getName(), "");
            } else if (param instanceof ElementParameter) {
                ModelElement element = (ModelElement) this.session.getModel().findById(metamodel.getMClass(param.getMetaclass()), param.getId());
                values.put(param.getName(), element);
            } else if (param instanceof ConstantParameter) {
                ModelElement element = (ModelElement) this.session.getModel().findById(metamodel.getMClass(param.getMetaclass()), param.getId());
                values.put(param.getName(), element);
            }
        }
        return values;
    }

    @objid ("5898b954-bdfb-4f8c-8e6d-1a2ae51a9a06")
    public Map<String, Object> getParameterValues() {
        return this.parameterValues;
    }

    @objid ("746c828d-932c-4466-bdf3-1f7b2eb15983")
    public RuntimePattern getPattern() {
        return this.pattern;
    }

    @objid ("566d9a4f-fddf-4acd-94a9-a748b63908c4")
    public ICoreSession getSession() {
        return this.session;
    }

    @objid ("ac4b9d0a-33ba-4ab7-ad96-9ffa73339658")
    public IModelioPickingService getPickingService() {
        return this.pickingService;
    }

}
