/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

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
 * Proxy class to handle a {@link PropertyTableDefinition} with << risk_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c3f8d3e7-1c4c-4ac8-b29b-91c5210bbdc0")
public class RiskPropertyset {
    @objid ("5f4fbace-1a37-4035-a886-e23b119f9baf")
    public static final String STEREOTYPE_NAME = "risk_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("1b843561-991b-41e8-863e-05c29c0ec361")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RiskPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5a7ba72e-cbef-472a-ae67-f4f7e5a91397")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << risk_propertyset >> then instantiate a {@link RiskPropertyset} proxy.
     * 
     * @return a {@link RiskPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("f5fe700e-5806-452c-97b3-895c87a06316")
    public static RiskPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskPropertyset.STEREOTYPE_NAME);
        return RiskPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RiskPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << risk_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RiskPropertyset} proxy or <i>null</i>.
     */
    @objid ("7c3d9ba0-45f8-4acf-b04f-7cd676c3c39e")
    public static RiskPropertyset instantiate(PropertyTableDefinition obj) {
        return RiskPropertyset.canInstantiate(obj) ? new RiskPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RiskPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << risk_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link RiskPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("2e1b458b-4b07-4580-8a36-9ccf3871039c")
    public static RiskPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RiskPropertyset.canInstantiate(obj))
        	return new RiskPropertyset(obj);
        else
        	throw new IllegalArgumentException("RiskPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("160e7760-029c-4fb5-87d0-ec163996da64")
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
        RiskPropertyset other = (RiskPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("8b346c2a-70e6-477a-9e4e-5285bd1aeb30")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("93a25f6e-0ada-419b-acd4-63eb9fd87048")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b646d588-62b7-4531-9477-efd78933ccd7")
    protected RiskPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("aca15579-4c69-4a63-8444-925d2d65d8fe")
    public static final class MdaTypes {
        @objid ("7ec09195-ccab-4e9b-b7f6-4986bb11cb47")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c8fdb9ab-7d07-44bb-975b-623c42bf95b8")
        private static Stereotype MDAASSOCDEP;

        @objid ("aab5cb1f-b565-4773-ae20-c77b6101e1d0")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b63aba83-1aad-4d29-bc83-cf87d979541f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "679a9417-8f06-4255-a409-1e1f7136e418");
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
