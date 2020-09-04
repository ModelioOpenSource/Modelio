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
 * Proxy class to handle a {@link InputPin} with << UML2RemoveAt >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e9bdfdee-c4b7-4b08-8bf3-1518a048df86")
public class UML2RemoveAt {
    @objid ("b87e19f5-92e1-41b4-b764-cf7f05a2dea1")
    public static final String STEREOTYPE_NAME = "UML2RemoveAt";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("789fc7c4-aab4-45b2-a9e9-ec9123a9e2a4")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2RemoveAt proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2RemoveAt >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d2feace2-54b5-4872-9dae-25c88f0c8869")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2RemoveAt >> then instantiate a {@link UML2RemoveAt} proxy.
     * 
     * @return a {@link UML2RemoveAt} proxy on the created {@link InputPin}.
     */
    @objid ("0ad1e44c-e98d-4891-bdba-e0ae8415fcfd")
    public static UML2RemoveAt create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2RemoveAt.STEREOTYPE_NAME);
        return UML2RemoveAt.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2RemoveAt} proxy or <i>null</i>.
     */
    @objid ("a8aece63-d8d5-4722-acc1-8931c086e607")
    public static UML2RemoveAt instantiate(InputPin obj) {
        return UML2RemoveAt.canInstantiate(obj) ? new UML2RemoveAt(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2RemoveAt} proxy from a {@link InputPin} stereotyped << UML2RemoveAt >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2RemoveAt} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9d5dbeb4-b022-4502-9a83-6ee05b7672b1")
    public static UML2RemoveAt safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2RemoveAt.canInstantiate(obj))
        	return new UML2RemoveAt(obj);
        else
        	throw new IllegalArgumentException("UML2RemoveAt: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("58ce0c7e-4ef6-47d7-9429-9d837935882b")
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
        UML2RemoveAt other = (UML2RemoveAt) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("e6e20d84-fd46-4f83-a41a-cd3d98daaa1b")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("d8e05768-6fa8-491c-8d19-23a82fb57afd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6603e181-2e96-4e70-9742-f3c149c093b1")
    protected UML2RemoveAt(InputPin elt) {
        this.elt = elt;
    }

    @objid ("140353cd-0401-48a1-a01c-d3f021559f83")
    public static final class MdaTypes {
        @objid ("a30177be-3998-4114-8399-72ab2fa13db0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("80acb54a-9547-4cfc-9365-7c91119237ab")
        private static Stereotype MDAASSOCDEP;

        @objid ("3f60f910-034a-46cf-95e2-5f130856f0cf")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("404f6f39-8ee1-4e03-8297-214fecfbb4ea")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "407d1bab-d29f-4f92-b12f-01283c1cc7eb");
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
