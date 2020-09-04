/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.outputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OutputPin;
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
 * Proxy class to handle a {@link OutputPin} with << UML2Decider >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3b0b1cd2-7362-4d64-8798-cb185dd04589")
public class UML2Decider {
    @objid ("3cd1953c-1129-44e3-9e7d-7ab1b635d853")
    public static final String STEREOTYPE_NAME = "UML2Decider";

    /**
     * The underlying {@link OutputPin} represented by this proxy, never null.
     */
    @objid ("cae33691-82d8-4e9c-9d66-7363f978bd02")
    protected final OutputPin elt;

    /**
     * Tells whether a {@link UML2Decider proxy} can be instantiated from a {@link MObject} checking it is a {@link OutputPin} stereotyped << UML2Decider >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9952fc49-7362-4b8f-b2be-6ef113990286")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OutputPin) && ((OutputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OutputPin} stereotyped << UML2Decider >> then instantiate a {@link UML2Decider} proxy.
     * 
     * @return a {@link UML2Decider} proxy on the created {@link OutputPin}.
     */
    @objid ("5ab20da0-7537-41b4-96cc-8bc703da3f71")
    public static UML2Decider create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OutputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Decider.STEREOTYPE_NAME);
        return UML2Decider.instantiate((OutputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OutputPin
     * @return a {@link UML2Decider} proxy or <i>null</i>.
     */
    @objid ("ef810cd9-a91b-485e-b057-d4ba14598ab2")
    public static UML2Decider instantiate(OutputPin obj) {
        return UML2Decider.canInstantiate(obj) ? new UML2Decider(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Decider} proxy from a {@link OutputPin} stereotyped << UML2Decider >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OutputPin}
     * @return a {@link UML2Decider} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bddebc77-851f-43b5-a5e5-b8a859bb2c45")
    public static UML2Decider safeInstantiate(OutputPin obj) throws IllegalArgumentException {
        if (UML2Decider.canInstantiate(obj))
        	return new UML2Decider(obj);
        else
        	throw new IllegalArgumentException("UML2Decider: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2c738322-584c-4d84-abcd-9046f3837d4a")
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
        UML2Decider other = (UML2Decider) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OutputPin}. 
     * @return the OutputPin represented by this proxy, never null.
     */
    @objid ("ed758b0d-74c5-48d9-a68d-5b72b06f235f")
    public OutputPin getElement() {
        return this.elt;
    }

    @objid ("939f0e3b-b8f2-4daf-9071-7c26ac5a3b6c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("aed3867e-ae28-4ed6-8d24-0cb786eb2338")
    protected UML2Decider(OutputPin elt) {
        this.elt = elt;
    }

    @objid ("31eec6ec-04ba-451c-a15c-cacea4d48a26")
    public static final class MdaTypes {
        @objid ("4c026f91-bb45-4874-a875-6afb535ee9c1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fcac3383-790a-4db0-89c3-05abcd570563")
        private static Stereotype MDAASSOCDEP;

        @objid ("40d12b33-8f75-4ea2-bd04-7d3fe1870a41")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c097a5e-3c4d-4e9c-abb9-f8a45be4c8fd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "27ac6d48-32c8-11e0-91f3-0027103f347c");
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
