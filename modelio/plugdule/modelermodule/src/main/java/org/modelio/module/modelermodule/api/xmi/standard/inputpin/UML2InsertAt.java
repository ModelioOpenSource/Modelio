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
 * Proxy class to handle a {@link InputPin} with << UML2InsertAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42686ded-7b6b-453d-ab60-76a6e2b6d99a")
public class UML2InsertAt {
    @objid ("65c889d5-ec15-4848-9ea8-32d8d5587952")
    public static final String STEREOTYPE_NAME = "UML2InsertAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("b1c6e88e-b3cb-40bb-8401-aadb3995dfec")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InsertAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InsertAt >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("537280e8-d27a-423e-a0ef-c807efe042b3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InsertAt >> then instantiate a {@link UML2InsertAt} proxy.
     * 
     * @return a {@link UML2InsertAt} proxy on the created {@link InputPin}.
     */
    @objid ("199a4639-92f1-4950-a1c2-0eebbdb68a67")
    public static UML2InsertAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InsertAt.STEREOTYPE_NAME);
        return UML2InsertAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InsertAt} proxy or <i>null</i>.
     */
    @objid ("ce5bc8e8-24b8-4d29-8f76-1420050fe99f")
    public static UML2InsertAt instantiate(InputPin obj) {
        return UML2InsertAt.canInstantiate(obj) ? new UML2InsertAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InsertAt} proxy from a {@link InputPin} stereotyped << UML2InsertAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InsertAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("98cba4e0-1633-4bab-b3ff-e2b258cbf995")
    public static UML2InsertAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InsertAt.canInstantiate(obj))
        	return new UML2InsertAt(obj);
        else
        	throw new IllegalArgumentException("UML2InsertAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3d43cc28-2ab3-4acf-9049-8f7921210c88")
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
        UML2InsertAt other = (UML2InsertAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("20d810fc-b7c5-430a-b335-b1d6bb414d0d")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("59f380af-3163-4a9c-88da-8c298158112e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("62f9f692-cd4d-4da0-8fae-07846016a39e")
    protected UML2InsertAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("3488919b-4d36-47de-bef3-5b5290d81e53")
    public static final class MdaTypes {
        @objid ("4ff150f3-4f32-46a8-ad9b-6958d0324512")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("331f0374-5bc9-4898-9b94-eda957755b08")
        private static Stereotype MDAASSOCDEP;

        @objid ("fc4988e5-b5f9-4efb-bfb2-852232170504")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a6891e04-e44b-4072-a140-ed11f36e8d4d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ec22d8ff-de86-11de-905b-001302895b2b");
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
