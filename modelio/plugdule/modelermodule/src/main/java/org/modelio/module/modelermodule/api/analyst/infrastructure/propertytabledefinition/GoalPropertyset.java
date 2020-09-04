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
 * Proxy class to handle a {@link PropertyTableDefinition} with << goal_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d01ebb18-da75-4ebb-96d8-94d22c850452")
public class GoalPropertyset {
    @objid ("5dc4112e-135c-486c-8dab-1b01e80bb2bd")
    public static final String STEREOTYPE_NAME = "goal_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("bf6b2ad4-445f-4b7e-a6a8-b104f8869460")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link GoalPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("14be181f-cd71-4214-835c-49ffc0d5b7b9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << goal_propertyset >> then instantiate a {@link GoalPropertyset} proxy.
     * 
     * @return a {@link GoalPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("3ce5d976-d7b5-41b3-8173-69ca493df7f1")
    public static GoalPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalPropertyset.STEREOTYPE_NAME);
        return GoalPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link GoalPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << goal_propertyset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link GoalPropertyset} proxy or <i>null</i>.
     */
    @objid ("721e38ba-e3a8-41be-8f8a-39b32d9f5ff8")
    public static GoalPropertyset instantiate(PropertyTableDefinition obj) {
        return GoalPropertyset.canInstantiate(obj) ? new GoalPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link GoalPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << goal_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link GoalPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("05bcdeb6-be89-4e99-9d43-727a205f9b5c")
    public static GoalPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (GoalPropertyset.canInstantiate(obj))
        	return new GoalPropertyset(obj);
        else
        	throw new IllegalArgumentException("GoalPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("364851f5-26a0-43ae-9b9d-0f8e647da0b7")
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
        GoalPropertyset other = (GoalPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("7475c49b-dd29-4e18-9db3-e57229cb6b11")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("afa6b5c5-a867-42bb-976e-2453acaa6841")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("66c57dd8-74d7-4c45-a0bb-bc482da2bd3d")
    protected GoalPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("af94a5ac-43a2-4dea-868e-bb40136af1d6")
    public static final class MdaTypes {
        @objid ("57047dd6-8bed-4975-9aeb-744525484947")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("063b5608-1571-4d07-a6e6-7f64946d781d")
        private static Stereotype MDAASSOCDEP;

        @objid ("548d048a-7690-4d12-930e-76a8f0057dca")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aa3aa258-3384-434d-8633-fb63861e9163")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12f2-0000-000000000000");
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
