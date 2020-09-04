/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.operation;

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
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Operation} with << UML2Reception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33ec2a3b-ce08-4728-96a1-13288d8a2e8d")
public class UML2Reception {
    @objid ("3e5966b7-37e8-4810-9e21-39457f633d1d")
    public static final String STEREOTYPE_NAME = "UML2Reception";

    /**
     * The underlying {@link Operation} represented by this proxy, never null.
     */
    @objid ("15f89626-22ab-4581-bc0b-08843a60d847")
    protected final Operation elt;

    /**
     * Tells whether a {@link UML2Reception proxy} can be instantiated from a {@link MObject} checking it is a {@link Operation} stereotyped << UML2Reception >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f2a9e509-acd8-4826-804d-02214005acea")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Operation) && ((Operation) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Operation} stereotyped << UML2Reception >> then instantiate a {@link UML2Reception} proxy.
     * 
     * @return a {@link UML2Reception} proxy on the created {@link Operation}.
     */
    @objid ("2cb8f522-ca1d-4157-bdeb-a29657699312")
    public static UML2Reception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Operation");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Reception.STEREOTYPE_NAME);
        return UML2Reception.instantiate((Operation)e);
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Operation
     * @return a {@link UML2Reception} proxy or <i>null</i>.
     */
    @objid ("c9bcd738-bc7f-4c76-8fb8-6299b13af218")
    public static UML2Reception instantiate(Operation obj) {
        return UML2Reception.canInstantiate(obj) ? new UML2Reception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Reception} proxy from a {@link Operation} stereotyped << UML2Reception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Operation}
     * @return a {@link UML2Reception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1c5705fe-c1bf-4a0e-afba-64876610de98")
    public static UML2Reception safeInstantiate(Operation obj) throws IllegalArgumentException {
        if (UML2Reception.canInstantiate(obj))
        	return new UML2Reception(obj);
        else
        	throw new IllegalArgumentException("UML2Reception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f4057990-051d-4b0c-a538-a054c617269d")
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
        UML2Reception other = (UML2Reception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Operation}. 
     * @return the Operation represented by this proxy, never null.
     */
    @objid ("c57657a0-06ad-4d2a-bdde-cfd37e67927b")
    public Operation getElement() {
        return this.elt;
    }

    @objid ("b9391e5f-7e9c-4a4b-ab61-1aa179e369f7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8768e22a-48d7-448a-8748-6629afb9cae4")
    protected UML2Reception(Operation elt) {
        this.elt = elt;
    }

    @objid ("0ae1c0c7-22eb-45ec-a199-bcb1a4c8b8ef")
    public static final class MdaTypes {
        @objid ("b2565820-f042-43db-ac48-7511b7576059")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d1353958-57a5-4530-b129-74881932bd74")
        private static Stereotype MDAASSOCDEP;

        @objid ("b2aae110-9843-4b7d-8f66-57b710d994dc")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9a4f6dd6-b23e-4dd6-9bf5-64ed1f4e78b8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a46b20b8-26ab-11df-ac88-001302895b2b");
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
