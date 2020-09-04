/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << impact_subroot >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("22b736f4-9a7f-47e0-addf-c7a7e736d6b7")
public class ImpactSubroot {
    @objid ("77851683-5438-4088-98dc-7d41cf390111")
    public static final String STEREOTYPE_NAME = "impact_subroot";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("e53da147-ac2c-482a-89c9-03fea876c200")
    protected final Dependency elt;

    /**
     * Tells whether a {@link ImpactSubroot proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << impact_subroot >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1de427e2-3e01-44ff-9b51-36920a900bf1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << impact_subroot >> then instantiate a {@link ImpactSubroot} proxy.
     * 
     * @return a {@link ImpactSubroot} proxy on the created {@link Dependency}.
     */
    @objid ("fd892365-fe3b-45e6-bf4d-1ac5fb9292f8")
    public static ImpactSubroot create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImpactSubroot.STEREOTYPE_NAME);
        return ImpactSubroot.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link ImpactSubroot} proxy or <i>null</i>.
     */
    @objid ("15baaf3f-ada2-43f5-91eb-fdd8ee8530fa")
    public static ImpactSubroot instantiate(Dependency obj) {
        return ImpactSubroot.canInstantiate(obj) ? new ImpactSubroot(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImpactSubroot} proxy from a {@link Dependency} stereotyped << impact_subroot >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link ImpactSubroot} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e2e23d1c-ad36-49e5-a5d2-379e0ce65b29")
    public static ImpactSubroot safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (ImpactSubroot.canInstantiate(obj))
        	return new ImpactSubroot(obj);
        else
        	throw new IllegalArgumentException("ImpactSubroot: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("d09d5a26-abae-4d08-a8fb-86f1c93c0317")
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
        ImpactSubroot other = (ImpactSubroot) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("5661f189-3590-406a-8919-60024ecf527c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("04b42e66-b295-41d3-91f1-1c13c0309d04")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("dcbc3002-70ba-4cea-acfd-3d13ae478001")
    protected ImpactSubroot(Dependency elt) {
        this.elt = elt;
    }

    @objid ("32cf63bf-4543-49bf-a3df-7d91136132db")
    public static final class MdaTypes {
        @objid ("36d1a012-eeeb-4be5-aeeb-93285abbe58e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("acb67755-fc39-4145-bd04-42252e42d5ff")
        private static Stereotype MDAASSOCDEP;

        @objid ("815f54bb-10dc-4fb9-9aa3-4f6eb313c5f3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b301a148-6a08-40d1-8439-a7913f79aadc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec2468-0000-0ac6-0000-000000000000");
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
