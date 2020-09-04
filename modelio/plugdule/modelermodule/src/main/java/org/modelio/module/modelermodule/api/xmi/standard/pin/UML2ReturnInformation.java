/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
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
 * Proxy class to handle a {@link Pin} with << UML2ReturnInformation >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a592276c-6700-4520-af3c-8bb54ab17733")
public class UML2ReturnInformation {
    @objid ("6326c5f5-4d4f-4f72-b1bb-f0f9f0a3c36e")
    public static final String STEREOTYPE_NAME = "UML2ReturnInformation";

    /**
     * The underlying {@link Pin} represented by this proxy, never null.
     */
    @objid ("67e79820-fb85-4bff-9e80-8d4d58bf4a46")
    protected final Pin elt;

    /**
     * Tells whether a {@link UML2ReturnInformation proxy} can be instantiated from a {@link MObject} checking it is a {@link Pin} stereotyped << UML2ReturnInformation >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2bce8a22-2ae1-413b-aa90-358323b40f1b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Pin) && ((Pin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Pin} stereotyped << UML2ReturnInformation >> then instantiate a {@link UML2ReturnInformation} proxy.
     * 
     * @return a {@link UML2ReturnInformation} proxy on the created {@link Pin}.
     */
    @objid ("5a739092-58ab-4f82-9223-8c642cb9b96e")
    public static UML2ReturnInformation create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Pin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReturnInformation.STEREOTYPE_NAME);
        return UML2ReturnInformation.instantiate((Pin)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Pin
     * @return a {@link UML2ReturnInformation} proxy or <i>null</i>.
     */
    @objid ("f5f729a8-562a-4f73-86b9-d7039d19328c")
    public static UML2ReturnInformation instantiate(Pin obj) {
        return UML2ReturnInformation.canInstantiate(obj) ? new UML2ReturnInformation(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReturnInformation} proxy from a {@link Pin} stereotyped << UML2ReturnInformation >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Pin}
     * @return a {@link UML2ReturnInformation} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7ed4f74c-9731-44b1-8a05-4a7ebf7a0557")
    public static UML2ReturnInformation safeInstantiate(Pin obj) throws IllegalArgumentException {
        if (UML2ReturnInformation.canInstantiate(obj))
        	return new UML2ReturnInformation(obj);
        else
        	throw new IllegalArgumentException("UML2ReturnInformation: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9f05f86c-b23e-4b2a-aada-1ae3c5289e3a")
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
        UML2ReturnInformation other = (UML2ReturnInformation) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Pin}. 
     * @return the Pin represented by this proxy, never null.
     */
    @objid ("0211b6c0-e3f0-443b-9822-6a3b1936716e")
    public Pin getElement() {
        return this.elt;
    }

    @objid ("d00ff4ae-7bd7-4a61-ae6d-6ee2205c1c8d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("3f412314-82e6-4b1d-8af4-36985b76c799")
    protected UML2ReturnInformation(Pin elt) {
        this.elt = elt;
    }

    @objid ("4350d302-8b4a-4b36-9f3e-09cc98ca12e6")
    public static final class MdaTypes {
        @objid ("d93f16a5-063a-4a85-9c7d-8140e9c720d6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ba36f411-b0eb-496d-864d-8d7cbdba5c4d")
        private static Stereotype MDAASSOCDEP;

        @objid ("9bc1317b-8f91-44a0-837e-d970c300090b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("fe0d6e28-f801-4b36-a348-9401bdefc27f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0936024c-9d51-4bc0-99b5-e8f72ae60007");
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
