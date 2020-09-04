/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
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
 * Proxy class to handle a {@link Signal} with << exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9d36d02b-ca62-4e9d-ac9f-5616c922fdd2")
public class Exception {
    @objid ("9c483224-9c86-4c25-a49e-de8c395a519c")
    public static final String STEREOTYPE_NAME = "exception";

    /**
     * The underlying {@link Signal} represented by this proxy, never null.
     */
    @objid ("caf4cd87-aa74-4b7d-ba00-8e095ee722c3")
    protected final Signal elt;

    /**
     * Tells whether a {@link Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link Signal} stereotyped << exception >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("a93e2af6-d3be-4141-ad35-f3150a5a94b6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Signal) && ((Signal) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Signal} stereotyped << exception >> then instantiate a {@link Exception} proxy.
     * 
     * @return a {@link Exception} proxy on the created {@link Signal}.
     */
    @objid ("b200c5cf-ed52-416f-901c-3a030024d5a8")
    public static Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Signal");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Exception.STEREOTYPE_NAME);
        return Exception.instantiate((Signal)e);
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Signal
     * @return a {@link Exception} proxy or <i>null</i>.
     */
    @objid ("77c9985e-c519-48bb-b0fe-ecaf2d72deb4")
    public static Exception instantiate(Signal obj) {
        return Exception.canInstantiate(obj) ? new Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Exception} proxy from a {@link Signal} stereotyped << exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Signal}
     * @return a {@link Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a2238a60-a439-4f56-8f8a-4aa2fb8b48e5")
    public static Exception safeInstantiate(Signal obj) throws IllegalArgumentException {
        if (Exception.canInstantiate(obj))
        	return new Exception(obj);
        else
        	throw new IllegalArgumentException("Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("bd7cd948-b044-4a56-bc9a-16106fb70ca9")
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
        Exception other = (Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Signal}. 
     * @return the Signal represented by this proxy, never null.
     */
    @objid ("3d349426-bb38-4431-b3ad-157ea2aaaf10")
    public Signal getElement() {
        return this.elt;
    }

    @objid ("db3045e2-5e1d-4f3f-8bcb-6fe73d1a6597")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1a61aa8c-1579-44aa-b947-b3436980411d")
    protected Exception(Signal elt) {
        this.elt = elt;
    }

    @objid ("9d616c88-ee7e-4e3e-a81d-c779a5fefc14")
    public static final class MdaTypes {
        @objid ("d4962e36-b306-4df9-a998-bb02400b5e56")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fc2d82e5-160b-4a30-b123-ce7b8eb08f35")
        private static Stereotype MDAASSOCDEP;

        @objid ("a0780e8b-4c75-4442-bbaa-91acd689b9f8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9a9deca0-d0ee-404a-a2ab-e6923754e012")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d1-0000-000000000000");
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
