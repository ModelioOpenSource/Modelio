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
 * Proxy class to handle a {@link InputPin} with << UML2Node >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ea8b4221-c8cb-4251-aacd-1582dd0f6eed")
public class UML2Node {
    @objid ("b9fb28a3-8c58-4522-b106-3a9d90ff2a13")
    public static final String STEREOTYPE_NAME = "UML2Node";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("bda1f28b-e4d7-4571-8c0d-672d0f2115a5")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Node proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Node >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2656a178-88ba-45c2-997d-63b001bc39a0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Node >> then instantiate a {@link UML2Node} proxy.
     * 
     * @return a {@link UML2Node} proxy on the created {@link InputPin}.
     */
    @objid ("b88c3785-204b-4b45-a7c3-7159c9325d36")
    public static UML2Node create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Node.STEREOTYPE_NAME);
        return UML2Node.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Node} proxy from a {@link InputPin} stereotyped << UML2Node >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Node} proxy or <i>null</i>.
     */
    @objid ("5245222f-cbb9-47a4-9407-94b864cef894")
    public static UML2Node instantiate(InputPin obj) {
        return UML2Node.canInstantiate(obj) ? new UML2Node(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Node} proxy from a {@link InputPin} stereotyped << UML2Node >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Node} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("48677ad6-7022-4dca-9f81-3fde4be1c980")
    public static UML2Node safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Node.canInstantiate(obj))
        	return new UML2Node(obj);
        else
        	throw new IllegalArgumentException("UML2Node: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3a347b5a-6ff4-46c9-b224-ceadc6625b04")
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
        UML2Node other = (UML2Node) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("1b0c3d98-bb32-468c-b9d8-1c151adec312")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("c13345e2-e843-4f36-85ed-8c02dacca6c6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("991b3d6b-7c3c-49d3-85dc-c5eb4f5a8a2d")
    protected UML2Node(InputPin elt) {
        this.elt = elt;
    }

    @objid ("0c3d3262-938a-44eb-ac4e-1a698f70a47a")
    public static final class MdaTypes {
        @objid ("360a4b1a-18e3-4813-b37c-6eb19e3fa993")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d3066d03-1fed-42f4-b01e-3070d98cea11")
        private static Stereotype MDAASSOCDEP;

        @objid ("afbe0238-b5c5-412f-b9ef-5773dd2288a2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("837507a7-be2b-41e4-99e8-2afd1de5b475")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1a4b1794-3302-4d19-9d02-077990211db2");
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
