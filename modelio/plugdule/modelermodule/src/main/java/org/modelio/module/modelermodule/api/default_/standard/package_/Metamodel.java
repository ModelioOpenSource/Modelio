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
 * Proxy class to handle a {@link Package} with << metamodel >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a997496b-c74c-4bae-8064-327d1bfe98c0")
public class Metamodel {
    @objid ("c6c25c9c-f590-417f-9913-58d099c3d27e")
    public static final String STEREOTYPE_NAME = "metamodel";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("136af3f1-ea28-4b40-a883-954e65eec1d8")
    protected final Package elt;

    /**
     * Tells whether a {@link Metamodel proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << metamodel >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8dc005bc-193e-48df-987b-24ae7fb0200f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << metamodel >> then instantiate a {@link Metamodel} proxy.
     * 
     * @return a {@link Metamodel} proxy on the created {@link Package}.
     */
    @objid ("0e0bf650-af39-4f5c-b517-b62d2b5a19eb")
    public static Metamodel create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Metamodel.STEREOTYPE_NAME);
        return Metamodel.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Metamodel} proxy or <i>null</i>.
     */
    @objid ("d3b1bb59-229d-44e2-af80-f2eb4c7327a7")
    public static Metamodel instantiate(Package obj) {
        return Metamodel.canInstantiate(obj) ? new Metamodel(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Metamodel} proxy from a {@link Package} stereotyped << metamodel >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Metamodel} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9074f14c-396a-487c-9a46-d4aec3f4ee10")
    public static Metamodel safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Metamodel.canInstantiate(obj))
        	return new Metamodel(obj);
        else
        	throw new IllegalArgumentException("Metamodel: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("30c8c35c-802a-47bd-ad29-4798ee4fcd93")
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
        Metamodel other = (Metamodel) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("800b5f42-f98f-4b31-8890-b99c27724194")
    public Package getElement() {
        return this.elt;
    }

    @objid ("1d8d84e8-644f-4f5e-9973-0b19b1961701")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7dce50a4-7003-4dbf-bbab-b24006167be8")
    protected Metamodel(Package elt) {
        this.elt = elt;
    }

    @objid ("272fd8c2-9080-44cd-9066-fd064321d028")
    public static final class MdaTypes {
        @objid ("68be61dd-6932-4c02-bbbf-a612dfb7ee73")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("23c76343-bc9f-4007-874f-61e8ed16ef39")
        private static Stereotype MDAASSOCDEP;

        @objid ("bf1d4a04-ce93-4b1c-8978-094b3ecb8633")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8fdeb3ae-cfac-422a-809a-decae3e7b181")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e7-0000-000000000000");
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
