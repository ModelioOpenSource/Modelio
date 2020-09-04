/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << stub >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1dc07035-e0be-493c-8a41-81d1f3e2bbfe")
public class Stub {
    @objid ("ae65a133-64a6-4d2f-aed6-b8cb2f21075a")
    public static final String STEREOTYPE_NAME = "stub";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("f821b280-ef0e-42d5-91d3-7372057dcd26")
    protected final Package elt;

    /**
     * Tells whether a {@link Stub proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << stub >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8c9d2c24-f62d-4f87-bf5d-df098569d61b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << stub >> then instantiate a {@link Stub} proxy.
     * 
     * @return a {@link Stub} proxy on the created {@link Package}.
     */
    @objid ("dbc0d4e9-5a75-4465-b3a8-56a289d323e1")
    public static Stub create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Stub.STEREOTYPE_NAME);
        return Stub.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Stub} proxy or <i>null</i>.
     */
    @objid ("b2f6ab1c-f656-4eb0-929b-7d510ded9283")
    public static Stub instantiate(Package obj) {
        return Stub.canInstantiate(obj) ? new Stub(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Stub} proxy from a {@link Package} stereotyped << stub >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Stub} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9f185556-a5a3-4503-8fdb-4ea0152cbf80")
    public static Stub safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Stub.canInstantiate(obj))
        	return new Stub(obj);
        else
        	throw new IllegalArgumentException("Stub: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("0495b934-6ee1-47ae-8ddd-3c913f43f1d2")
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
        Stub other = (Stub) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("743d51a0-d243-4dd5-9972-83ddb6876acb")
    public Package getElement() {
        return this.elt;
    }

    @objid ("1d974971-62a1-4189-8e01-d3cbd049f93d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7cf9e37a-955e-4bfe-8d02-52a7315a39d3")
    protected Stub(Package elt) {
        this.elt = elt;
    }

    @objid ("cbe40972-a0dc-4f70-a0d4-7a45c10fbcc0")
    public static final class MdaTypes {
        @objid ("efb82fe2-e224-4014-af2d-bf0f5cb91ff9")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d45a8ed3-ed36-42f4-b3d1-5795ff105f6c")
        private static Stereotype MDAASSOCDEP;

        @objid ("dd62b310-eb2d-419d-92b9-20d302a33274")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("642a7c71-9b10-487f-8217-92b8861c9842")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d7-0000-000000000000");
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
