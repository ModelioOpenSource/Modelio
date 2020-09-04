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
 * Proxy class to handle a {@link PropertyTableDefinition} with << requirement_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("406ee77a-a498-461f-bd3f-0a9f8dab7754")
public class RequirementPropertyset {
    @objid ("c54ced4c-5a27-4ee9-ab75-8bacf0b899ae")
    public static final String STEREOTYPE_NAME = "requirement_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("4a2d2ea8-aa39-4471-9c21-e3d40b173a49")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link RequirementPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("46a72b39-41db-4245-b8aa-b855d374e5fc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> then instantiate a {@link RequirementPropertyset} proxy.
     * 
     * @return a {@link RequirementPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("236675f8-9cc1-4689-94a1-f2a0a4b8767e")
    public static RequirementPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RequirementPropertyset.STEREOTYPE_NAME);
        return RequirementPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link RequirementPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link RequirementPropertyset} proxy or <i>null</i>.
     */
    @objid ("98bfea35-91fa-460c-8204-acd80bb9d555")
    public static RequirementPropertyset instantiate(PropertyTableDefinition obj) {
        return RequirementPropertyset.canInstantiate(obj) ? new RequirementPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RequirementPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << requirement_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link RequirementPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("15d924ad-0658-42b2-a45b-96bfac28cf6b")
    public static RequirementPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (RequirementPropertyset.canInstantiate(obj))
        	return new RequirementPropertyset(obj);
        else
        	throw new IllegalArgumentException("RequirementPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b32f51ed-8fb6-4716-991c-9457172699fb")
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
        RequirementPropertyset other = (RequirementPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("92bd0e12-16fe-47e8-ab94-08f7503ea3f5")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("18fef16c-ba1b-4dcd-b752-06a8d7d3bdbd")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ec2bdae0-70fb-4e31-9e1a-d7077a67defe")
    protected RequirementPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("8fb90d31-ee90-41bf-8357-2e601bf691dc")
    public static final class MdaTypes {
        @objid ("3bbc21b5-a9f2-4ea9-a40b-b3827a3d37a4")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("65fc4ebf-d64f-455e-ab49-496e2569478b")
        private static Stereotype MDAASSOCDEP;

        @objid ("6c85eb8b-684d-4e32-9fc5-447ae321e24c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1b3c0c8d-22f9-4649-9803-a2a46ddba9f6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12f7-0000-000000000000");
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
