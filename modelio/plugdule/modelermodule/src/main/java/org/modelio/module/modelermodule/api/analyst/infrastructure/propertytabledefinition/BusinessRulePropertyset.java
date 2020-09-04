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
 * Proxy class to handle a {@link PropertyTableDefinition} with << business_rule_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c2b1093f-2962-4523-99fe-38f70a53ddad")
public class BusinessRulePropertyset {
    @objid ("d813d4b3-2fe5-43da-85e9-1f34e5a71578")
    public static final String STEREOTYPE_NAME = "business_rule_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("49e8229f-a9b9-47a4-a5a9-a5914d0a0ccd")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link BusinessRulePropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6efd78fd-ac88-4eb4-99ce-e36164aa664b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> then instantiate a {@link BusinessRulePropertyset} proxy.
     * 
     * @return a {@link BusinessRulePropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("60c100c0-4d57-4904-948d-c6450ae71e11")
    public static BusinessRulePropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, BusinessRulePropertyset.STEREOTYPE_NAME);
        return BusinessRulePropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link BusinessRulePropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link BusinessRulePropertyset} proxy or <i>null</i>.
     */
    @objid ("5ecc151e-d6ae-433d-8b33-61d835c43807")
    public static BusinessRulePropertyset instantiate(PropertyTableDefinition obj) {
        return BusinessRulePropertyset.canInstantiate(obj) ? new BusinessRulePropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link BusinessRulePropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << business_rule_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link BusinessRulePropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1d00a6b7-9180-4568-9d58-0162610b3a4b")
    public static BusinessRulePropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (BusinessRulePropertyset.canInstantiate(obj))
        	return new BusinessRulePropertyset(obj);
        else
        	throw new IllegalArgumentException("BusinessRulePropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4603a050-b338-4bf6-86d4-eb1ec4b0fe00")
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
        BusinessRulePropertyset other = (BusinessRulePropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("86328b24-b4d2-43ea-a034-14eb45479169")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("c7312008-8234-45e6-be85-af8d1c1244fa")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d3ff03a4-cd93-4555-8667-7212600ceed2")
    protected BusinessRulePropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("dd832859-edff-49b3-945e-65d2c349b0b0")
    public static final class MdaTypes {
        @objid ("b4842b38-986c-43f9-b3a4-8370f117a7f0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7f7d54b3-f3dc-4461-9b97-62be9dde39d8")
        private static Stereotype MDAASSOCDEP;

        @objid ("ec89910c-96bf-4620-84e6-50389e6bd520")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9d327863-6bb7-4a3d-b118-2ee2a68ee9b8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-1301-0000-000000000000");
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
