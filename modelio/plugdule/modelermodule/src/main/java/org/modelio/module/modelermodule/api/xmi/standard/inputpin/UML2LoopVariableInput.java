/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link InputPin} with << UML2LoopVariableInput >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("6a8233a5-9eb3-40a5-83aa-e84ebe4d59b4")
public class UML2LoopVariableInput {
    @objid ("b62168f2-2409-435f-bb98-6b4c6a9f35bb")
    public static final String STEREOTYPE_NAME = "UML2LoopVariableInput";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("01907569-559b-4110-a6ae-92dac5bc68c8")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2LoopVariableInput proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2LoopVariableInput >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8e9574e7-3866-4252-9bd3-55a81d7d1bfc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2LoopVariableInput >> then instantiate a {@link UML2LoopVariableInput} proxy.
     * 
     * @return a {@link UML2LoopVariableInput} proxy on the created {@link InputPin}.
     */
    @objid ("42299861-08a4-497a-ae1f-c51ff8779573")
    public static UML2LoopVariableInput create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2LoopVariableInput.STEREOTYPE_NAME);
        return UML2LoopVariableInput.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2LoopVariableInput} proxy or <i>null</i>.
     */
    @objid ("acf9c7ae-6ec9-4516-8a88-7b668f08d115")
    public static UML2LoopVariableInput instantiate(InputPin obj) {
        return UML2LoopVariableInput.canInstantiate(obj) ? new UML2LoopVariableInput(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2LoopVariableInput} proxy from a {@link InputPin} stereotyped << UML2LoopVariableInput >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2LoopVariableInput} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("625d03d9-0169-4c5a-81b9-cba032affa8a")
    public static UML2LoopVariableInput safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2LoopVariableInput.canInstantiate(obj))
        	return new UML2LoopVariableInput(obj);
        else
        	throw new IllegalArgumentException("UML2LoopVariableInput: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0d396f4f-98d1-4804-858d-b98d09f2599a")
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
        UML2LoopVariableInput other = (UML2LoopVariableInput) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("d346ba35-9be7-4b8e-b1a5-6a1c648a8646")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d120539d-0daa-43fe-bb92-8c959d56b715")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("487371bb-563d-49c2-ade9-be7224434bd8")
    protected UML2LoopVariableInput(InputPin elt) {
        this.elt = elt;
    }

    @objid ("2bccab18-b4c5-42a8-a3d6-068429925e3a")
    public static final class MdaTypes {
        @objid ("55c3632b-ce6e-4b60-bb18-aa2d1326add0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("131b12f7-d6ad-457e-9a68-e5fd00815277")
        private static Stereotype MDAASSOCDEP;

        @objid ("bdf119bf-3611-4f60-a687-6fb72d777bbf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b97d404f-cd64-46ba-84d9-8ca7260622be")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "7a7f049a-6b5f-4db9-9f79-8e327ca90297");
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
