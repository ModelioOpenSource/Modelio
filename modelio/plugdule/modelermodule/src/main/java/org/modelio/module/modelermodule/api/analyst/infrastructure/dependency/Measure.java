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
 * Proxy class to handle a {@link Dependency} with << measure >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a5520de-c3f4-49fe-82ab-dd4a9414a61c")
public class Measure {
    @objid ("4a394e81-fbdd-425e-a9ba-f05236b14b23")
    public static final String STEREOTYPE_NAME = "measure";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ce0a9594-ffe2-409b-adc1-f7c84d1cf7f5")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Measure proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << measure >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6b44f780-b79a-4a19-9708-edff3ebe0594")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << measure >> then instantiate a {@link Measure} proxy.
     * 
     * @return a {@link Measure} proxy on the created {@link Dependency}.
     */
    @objid ("cddab178-ea31-4eef-b0ee-db0a1274150c")
    public static Measure create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Measure.STEREOTYPE_NAME);
        return Measure.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Measure} proxy or <i>null</i>.
     */
    @objid ("b7f27203-aed6-49ab-82c5-1062d766a96b")
    public static Measure instantiate(Dependency obj) {
        return Measure.canInstantiate(obj) ? new Measure(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Measure} proxy from a {@link Dependency} stereotyped << measure >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Measure} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("39414f33-c09d-4bea-bce0-66fd30b0ed0f")
    public static Measure safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Measure.canInstantiate(obj))
        	return new Measure(obj);
        else
        	throw new IllegalArgumentException("Measure: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ab30b7e0-edb4-42b9-a72e-b0484e6e8872")
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
        Measure other = (Measure) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("07255fc3-f184-46ff-a4d8-0c9dba3aa459")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("2ee74416-c528-427a-8fbc-6c0fab57109e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7505719e-1be9-4de8-ba2e-ddd6d0cd00ee")
    protected Measure(Dependency elt) {
        this.elt = elt;
    }

    @objid ("46bb357b-ca38-48c0-b010-8be5578cd473")
    public static final class MdaTypes {
        @objid ("2a8506d2-96dd-4b34-abbe-3eb979bf1395")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3dd5577f-e9e6-47b5-a3b5-f30b29485ebf")
        private static Stereotype MDAASSOCDEP;

        @objid ("3fc365f8-77c4-478e-9afb-b78ab4dd0f62")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ecfa493f-c4f9-48a0-adca-fd305dfcf0be")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0256-0000-000000000000");
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
