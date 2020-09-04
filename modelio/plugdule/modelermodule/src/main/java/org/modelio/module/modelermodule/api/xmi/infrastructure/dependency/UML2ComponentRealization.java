/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2ComponentRealization >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("20892636-39d2-4f82-9ef5-673a7149b405")
public class UML2ComponentRealization {
    @objid ("75924373-ee30-4993-a305-db6ac552f63c")
    public static final String STEREOTYPE_NAME = "UML2ComponentRealization";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("f60eb971-ecd3-4c38-8a74-28d7267233c4")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ComponentRealization proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ComponentRealization >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("37e80e94-596a-4ad9-97ef-ae557789db93")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ComponentRealization >> then instantiate a {@link UML2ComponentRealization} proxy.
     * 
     * @return a {@link UML2ComponentRealization} proxy on the created {@link Dependency}.
     */
    @objid ("b3315cc9-242e-41a1-8823-acbef0083f60")
    public static UML2ComponentRealization create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ComponentRealization.STEREOTYPE_NAME);
        return UML2ComponentRealization.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ComponentRealization} proxy or <i>null</i>.
     */
    @objid ("43dd7070-808b-4b77-9290-878a44480217")
    public static UML2ComponentRealization instantiate(Dependency obj) {
        return UML2ComponentRealization.canInstantiate(obj) ? new UML2ComponentRealization(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ComponentRealization} proxy from a {@link Dependency} stereotyped << UML2ComponentRealization >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ComponentRealization} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5dc7f7a7-2dd2-4e0b-9c6e-559908780d29")
    public static UML2ComponentRealization safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ComponentRealization.canInstantiate(obj))
        	return new UML2ComponentRealization(obj);
        else
        	throw new IllegalArgumentException("UML2ComponentRealization: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("13c1ec11-3f2f-4405-8e5a-17702c7f4d72")
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
        UML2ComponentRealization other = (UML2ComponentRealization) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("4ce6d627-5c98-46cb-ba93-b79a28b51f87")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("47908b63-dcfa-4a23-a31c-dfa16cc099eb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("8abd70ef-f3b3-49f5-9165-955e42d62cb0")
    protected UML2ComponentRealization(Dependency elt) {
        this.elt = elt;
    }

    @objid ("c07294a9-7636-4649-a593-5c852a1a29bd")
    public static final class MdaTypes {
        @objid ("c81c592f-a767-4a0d-9857-6b711acd00f3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("22536c22-6e70-4f96-896c-05a3c73714eb")
        private static Stereotype MDAASSOCDEP;

        @objid ("9ccbdcae-a056-48f4-8bb1-64cb61f47b56")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("97610fd7-e3bd-471b-9cfa-1d6480834a88")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "99bf7919-5d09-11df-a996-001302895b2b");
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
