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
 * Proxy class to handle a {@link Package} with << facade >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de0809b5-09b8-4993-822d-cd90c93c207d")
public class Facade {
    @objid ("5231903e-17cf-44aa-b5bf-1e191dd7e8e8")
    public static final String STEREOTYPE_NAME = "facade";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("f296a258-ef75-4e5d-90ba-1ac87527cb31")
    protected final Package elt;

    /**
     * Tells whether a {@link Facade proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << facade >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e03c4c28-8397-4179-9bcf-5148c44d8f66")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << facade >> then instantiate a {@link Facade} proxy.
     * 
     * @return a {@link Facade} proxy on the created {@link Package}.
     */
    @objid ("0ab2bc84-6b55-48da-90d3-f0e0591f3ca1")
    public static Facade create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME);
        return Facade.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Facade} proxy or <i>null</i>.
     */
    @objid ("7947e605-e73a-467d-b227-274a886fc21f")
    public static Facade instantiate(Package obj) {
        return Facade.canInstantiate(obj) ? new Facade(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Facade} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d5028904-4edc-4c96-9b3a-dd41961d4e0e")
    public static Facade safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Facade.canInstantiate(obj))
        	return new Facade(obj);
        else
        	throw new IllegalArgumentException("Facade: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2f5cd07c-47f4-4f17-8455-88cbbd187f0a")
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
        Facade other = (Facade) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("52d31859-d8d9-47f2-b0bd-3a601132017c")
    public Package getElement() {
        return this.elt;
    }

    @objid ("29624b16-1501-439b-930b-643f683bb579")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("24f59f7d-77c4-4128-a550-55cd2159ea96")
    protected Facade(Package elt) {
        this.elt = elt;
    }

    @objid ("5a57592c-acc8-457a-899c-a8d0cc2b60df")
    public static final class MdaTypes {
        @objid ("b4d27f8d-202a-410b-a2a8-e4e5b2848234")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8236867f-48d9-42d3-87eb-d0a8ca376b95")
        private static Stereotype MDAASSOCDEP;

        @objid ("38388ab0-3fad-4c2b-94a2-e2721634f4cd")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c4ac2c41-d94d-4f39-9313-265cc5f0bd4a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d5-0000-000000000000");
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
