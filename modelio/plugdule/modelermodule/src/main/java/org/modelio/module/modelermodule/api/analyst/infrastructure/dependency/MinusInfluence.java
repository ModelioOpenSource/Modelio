/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << -influence >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9e2b42e5-717f-4b46-98c8-1e34cddc5f83")
public class MinusInfluence {
    @objid ("fc39e19e-9428-4ba8-a499-32621c38b225")
    public static final String STEREOTYPE_NAME = "-influence";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("80c33da6-cb9d-4396-9116-18967942783e")
    protected final Dependency elt;

    /**
     * Tells whether a {@link MinusInfluence proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << -influence >>.
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("67773f04-6c85-470f-8fbd-a0bfa8b458a3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << -influence >> then instantiate a {@link MinusInfluence} proxy.
     * @return a {@link MinusInfluence} proxy on the created {@link Dependency}.
     */
    @objid ("949bc2e4-809d-462a-b29c-907639256e9d")
    public static MinusInfluence create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, MinusInfluence.STEREOTYPE_NAME);
        return MinusInfluence.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >>checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link MinusInfluence} proxy or <i>null</i>.
     */
    @objid ("be4ca420-1d16-417d-bb32-fb6496b688a9")
    public static MinusInfluence instantiate(Dependency obj) {
        return MinusInfluence.canInstantiate(obj) ? new MinusInfluence(obj) : null;
    }

    /**
     * Tries to instantiate a {@link MinusInfluence} proxy from a {@link Dependency} stereotyped << -influence >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link MinusInfluence} proxy.
     * @throws java.lang.IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("69a77898-d274-425c-9165-e0a21224c76d")
    public static MinusInfluence safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (MinusInfluence.canInstantiate(obj))
            return new MinusInfluence(obj);
        else
            throw new IllegalArgumentException("MinusInfluence: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("149da29d-26b3-40e4-b903-65f7a07eac90")
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
        MinusInfluence other = (MinusInfluence) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}.
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("38aba229-8cc5-4c01-a862-f5d4b99bc283")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("4f3f1302-7838-4056-a096-a9677f1a1d78")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ad6bd3c2-5bff-4e58-8680-2d51aeffb5b9")
    protected MinusInfluence(Dependency elt) {
        this.elt = elt;
    }

    @objid ("4dd72b91-2fb2-4357-b513-96ce1e76264b")
    public static final class MdaTypes {
        @objid ("24cfd717-9dd1-4fd0-8d13-01f362240e11")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7f3db809-81fc-4280-9eb7-83c9404f9b0b")
        private static Stereotype MDAASSOCDEP;

        @objid ("0dc3d30e-0429-49bd-8819-774fa5657534")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a1459521-c322-4d72-830f-eb7891de0a4c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-024c-0000-000000000000");
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
