/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Parameter} with << UML2OperationTemplateParameter >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d3e40d9b-99b6-479e-872c-bab0545bb6db")
public class UML2OperationTemplateParameter {
    @objid ("af2b9220-b7c8-4de6-bd79-d6bc4e5af0b9")
    public static final String STEREOTYPE_NAME = "UML2OperationTemplateParameter";

    /**
     * The underlying {@link Parameter} represented by this proxy, never null.
     */
    @objid ("0119e0c8-457f-4c0d-a6ec-de8feebf467e")
    protected final Parameter elt;

    /**
     * Tells whether a {@link UML2OperationTemplateParameter proxy} can be instantiated from a {@link MObject} checking it is a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f7936e1a-03ff-43a0-b403-8a6942dcc135")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Parameter) && ((Parameter) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Parameter} stereotyped << UML2OperationTemplateParameter >> then instantiate a {@link UML2OperationTemplateParameter} proxy.
     * 
     * @return a {@link UML2OperationTemplateParameter} proxy on the created {@link Parameter}.
     */
    @objid ("9e384c0f-1924-4394-8e7b-cb0cfc221dff")
    public static UML2OperationTemplateParameter create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Parameter");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2OperationTemplateParameter.STEREOTYPE_NAME);
        return UML2OperationTemplateParameter.instantiate((Parameter)e);
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Parameter
     * @return a {@link UML2OperationTemplateParameter} proxy or <i>null</i>.
     */
    @objid ("dcaf7bb0-82e3-4382-b890-d38b3c42808c")
    public static UML2OperationTemplateParameter instantiate(Parameter obj) {
        return UML2OperationTemplateParameter.canInstantiate(obj) ? new UML2OperationTemplateParameter(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2OperationTemplateParameter} proxy from a {@link Parameter} stereotyped << UML2OperationTemplateParameter >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Parameter}
     * @return a {@link UML2OperationTemplateParameter} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8b8f152a-704f-4f75-977b-8db4aaf3e30c")
    public static UML2OperationTemplateParameter safeInstantiate(Parameter obj) throws IllegalArgumentException {
        if (UML2OperationTemplateParameter.canInstantiate(obj))
        	return new UML2OperationTemplateParameter(obj);
        else
        	throw new IllegalArgumentException("UML2OperationTemplateParameter: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("27603131-7392-4eda-9b54-2792348f38bd")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2OperationTemplateParameter other = (UML2OperationTemplateParameter) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Parameter}. 
     * @return the Parameter represented by this proxy, never null.
     */
    @objid ("66a38760-2357-43c8-b67f-84ecd0600438")
    public Parameter getElement() {
        return this.elt;
    }

    @objid ("477b2e48-c771-47a9-8a3f-8631cec44c76")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b0b537b7-0c52-4753-bfb5-4c907ee46e1d")
    protected UML2OperationTemplateParameter(Parameter elt) {
        this.elt = elt;
    }

    @objid ("f0c89734-17df-443d-90e3-d99c1720d92b")
    public static final class MdaTypes {
        @objid ("f105bbfc-52df-4776-9e46-68bc07f20990")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6c0d0ba5-20a3-431d-9f7c-06e9f68c773d")
        private static Stereotype MDAASSOCDEP;

        @objid ("100e9beb-1984-4e2d-835f-c567696e6389")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3cab2aa1-55fd-4b9a-9e96-5b286094c023")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07ba6521-5d0d-11df-a996-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
