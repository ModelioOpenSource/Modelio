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
 * Proxy class to handle a {@link InputPin} with << UML2Target >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c9ef7950-aef3-455f-bd22-02a260ca445a")
public class UML2Target {
    @objid ("b596e3d8-32ec-4a42-9f9c-53d7219eeabe")
    public static final String STEREOTYPE_NAME = "UML2Target";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("0de0255b-45b7-4bec-9358-04102e27b2b8")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Target proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Target >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3353e30d-215e-49b0-b16c-22f9a0efe8ee")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Target >> then instantiate a {@link UML2Target} proxy.
     * 
     * @return a {@link UML2Target} proxy on the created {@link InputPin}.
     */
    @objid ("49e0abdf-8a26-40a1-94c6-572c23f54429")
    public static UML2Target create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Target.STEREOTYPE_NAME);
        return UML2Target.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Target} proxy or <i>null</i>.
     */
    @objid ("93ad1357-d404-4052-a7b7-32ac7cdadab7")
    public static UML2Target instantiate(InputPin obj) {
        return UML2Target.canInstantiate(obj) ? new UML2Target(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Target} proxy from a {@link InputPin} stereotyped << UML2Target >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Target} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2e4008e7-9210-4c4c-8d08-53be9292bbc1")
    public static UML2Target safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Target.canInstantiate(obj))
        	return new UML2Target(obj);
        else
        	throw new IllegalArgumentException("UML2Target: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3c369272-aec9-4c3f-b606-1db917ea3def")
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
        UML2Target other = (UML2Target) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("953782d9-f878-4380-83bc-e09e56bcabe6")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("378511ed-dfe9-423d-8b43-56c63b19c9df")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("af395254-e019-4b26-8f20-3de7f54de65b")
    protected UML2Target(InputPin elt) {
        this.elt = elt;
    }

    @objid ("f7e8a899-0f5c-4177-848d-e2e7b037113a")
    public static final class MdaTypes {
        @objid ("0dc4ff2d-00d3-4111-b9f6-cdbde2439c65")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3df528c2-0d07-42df-b4e6-adacec0bdffc")
        private static Stereotype MDAASSOCDEP;

        @objid ("083076ad-1322-4863-a5e1-fcdbd96649bc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f3c92df6-5926-4b93-af89-60b987f239c0")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "aa99ee06-c495-11de-ada1-001302895b2b");
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
