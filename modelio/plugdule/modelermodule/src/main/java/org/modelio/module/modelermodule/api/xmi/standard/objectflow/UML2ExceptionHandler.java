/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.objectflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.ObjectFlow;
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
 * Proxy class to handle a {@link ObjectFlow} with << UML2ExceptionHandler >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("99f3e547-a9ff-492d-8072-1124b305e010")
public class UML2ExceptionHandler {
    @objid ("aec0a910-4293-4853-9b61-ebfa87c6ac74")
    public static final String STEREOTYPE_NAME = "UML2ExceptionHandler";

    /**
     * The underlying {@link ObjectFlow} represented by this proxy, never null.
     */
    @objid ("ba1cc939-665e-4218-ae09-cb07f6a61bda")
    protected final ObjectFlow elt;

    /**
     * Tells whether a {@link UML2ExceptionHandler proxy} can be instantiated from a {@link MObject} checking it is a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("fe0549ec-3a15-4458-a334-a54d084b9608")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ObjectFlow) && ((ObjectFlow) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> then instantiate a {@link UML2ExceptionHandler} proxy.
     * 
     * @return a {@link UML2ExceptionHandler} proxy on the created {@link ObjectFlow}.
     */
    @objid ("7e62b0a0-718f-4077-a320-631c6e6f09f4")
    public static UML2ExceptionHandler create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ObjectFlow");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionHandler.STEREOTYPE_NAME);
        return UML2ExceptionHandler.instantiate((ObjectFlow)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionHandler} proxy from a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ObjectFlow
     * @return a {@link UML2ExceptionHandler} proxy or <i>null</i>.
     */
    @objid ("75923deb-a8bf-433f-bd28-b94b84f8d519")
    public static UML2ExceptionHandler instantiate(ObjectFlow obj) {
        return UML2ExceptionHandler.canInstantiate(obj) ? new UML2ExceptionHandler(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionHandler} proxy from a {@link ObjectFlow} stereotyped << UML2ExceptionHandler >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ObjectFlow}
     * @return a {@link UML2ExceptionHandler} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f78f5438-b69d-4210-a950-773b6aa97ec6")
    public static UML2ExceptionHandler safeInstantiate(ObjectFlow obj) throws IllegalArgumentException {
        if (UML2ExceptionHandler.canInstantiate(obj))
        	return new UML2ExceptionHandler(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionHandler: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0f05d5d8-6eb7-46bd-8d91-67c2da8386a8")
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
        UML2ExceptionHandler other = (UML2ExceptionHandler) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ObjectFlow}. 
     * @return the ObjectFlow represented by this proxy, never null.
     */
    @objid ("6f4c2957-d602-4c61-b55a-3305017da025")
    public ObjectFlow getElement() {
        return this.elt;
    }

    @objid ("218a9acb-d13e-4543-8595-727677c05771")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa943e08-18c4-4051-8130-93cdb89bf1a1")
    protected UML2ExceptionHandler(ObjectFlow elt) {
        this.elt = elt;
    }

    @objid ("ed214f2b-6a8e-4515-9c8d-379b6f62a571")
    public static final class MdaTypes {
        @objid ("b8aeee54-de3f-4824-9224-5debcb436ec5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("6166ea45-1fcd-4249-b867-804c7421b248")
        private static Stereotype MDAASSOCDEP;

        @objid ("3a72b655-49d7-46d5-8cdf-9899632fa811")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("3cca4820-d68d-4455-a9ca-a2c3ca7079ac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1b7fd53f-205e-11df-948e-001302895b2b");
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
