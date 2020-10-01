/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.platform.mda.infra.service.impl.expert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.service.IModuleRegistry;
import org.modelio.platform.mda.infra.service.IRTModule;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of the {@link IMdaExpert} interface looking for actual experts provided by modules.
 * <p>
 * MDA experts are stored in a registry to diminish the lookup cost.
 * </p>
 */
@objid ("4352eb8a-a9e9-4aa5-bbbf-7c46202a7484")
public class MdaExpert implements IMdaExpert {
    @objid ("d02ea203-7cad-4a3f-9406-fa4ee4aed996")
    private final MdaExpertRegistry mdaRegistry;

    /**
     * C'tor.
     * 
     * @param iModuleRegistry module registry to access {@link IRTModule} instances.
     */
    @objid ("00964c31-d071-4f34-9457-45b11a1e0b0f")
    public MdaExpert(IModuleRegistry iModuleRegistry) {
        this.mdaRegistry = new MdaExpertRegistry(iModuleRegistry);
    }

    @objid ("4ef540ef-e23c-4b4b-82ae-79d35e673d10")
    @Override
    public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass from) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkStereotype);
        MExpert linkExpert = linkMetaclass.getMetamodel().getMExpert();
        return (stereotypeExpert.canSource(linkStereotype, linkMetaclass, from)) && linkExpert.canSource(linkMetaclass, from);
    }

    @objid ("d8ceb6b6-aa9c-435b-a4f7-99ea00f6c83f")
    @Override
    public boolean canSource(Stereotype linkStereotype, MObject linkMetaclass, MObject from) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkStereotype);
        MExpert linkExpert = linkMetaclass.getMClass().getMetamodel().getMExpert();
        return (stereotypeExpert.canSource(linkStereotype, linkMetaclass, from)) && linkExpert.canSource(linkMetaclass, from);
    }

    @objid ("19dc97d1-d1f8-4bbd-99fb-8d3311be2d55")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass to) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkStereotype);
        MExpert linkExpert = linkMetaclass.getMetamodel().getMExpert();
        return stereotypeExpert.canTarget(linkStereotype, linkMetaclass, to) && linkExpert.canTarget(linkMetaclass, to);
    }

    @objid ("49267cd8-47bb-4161-b1fb-f941c66325b1")
    @Override
    public boolean canTarget(Stereotype linkStereotype, MObject linkMetaclass, MObject to) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkStereotype);
        MExpert linkExpert = linkMetaclass.getMClass().getMetamodel().getMExpert();
        return stereotypeExpert.canTarget(linkStereotype, linkMetaclass, to) && linkExpert.canTarget(linkMetaclass, to);
    }

    @objid ("b37cca6d-5273-4626-9fb8-a7da9037531c")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject from, MObject to) {
        // Check MDA expert
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkStereotype);
        if (!stereotypeExpert.canLink(linkStereotype, linkMetaclass, from, to)) {
            return false;
        }
        
        // Check metaclass expert
        MExpert linkExpert = linkMetaclass.getMetamodel().getMExpert();
        return linkExpert.canLink(linkMetaclass, from, to);
    }

    @objid ("38815507-470d-4b2c-8499-cea3b40fdcb5")
    @Override
    public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
        // Check MDA expert
        IMdaExpert expert = this.mdaRegistry.getExpert(linkStereotype);
        if (!expert.canLink(linkStereotype, linkMetaclass, fromMetaclass, toMetaclass)) {
            return false;
        }
        
        // Check metaclass expert
        MExpert linkExpert = linkMetaclass.getMetamodel().getMExpert();
        return linkExpert.canLink(linkMetaclass, fromMetaclass, toMetaclass);
    }

    @objid ("278901ae-af62-4c4d-a7df-c389b6608c8f")
    @Override
    public boolean canLink(ElementScope linkScope, ElementScope fromScope, ElementScope toScope) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkScope.getStereotype());
        MExpert linkExpert = linkScope.getMetaclass().getMetamodel().getMExpert();
        return stereotypeExpert.canLink(linkScope, fromScope, toScope) && linkExpert.canLink(linkScope.getMetaclass(), fromScope.getMetaclass(), toScope.getMetaclass());
    }

    @objid ("29b4b248-823d-4415-b96e-244b07c0e5d4")
    @Override
    public boolean canSource(ElementScope linkScope, ElementScope fromScope) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkScope.getStereotype());
        MExpert linkExpert = linkScope.getMetaclass().getMetamodel().getMExpert();
        return stereotypeExpert.canSource(linkScope, fromScope) && linkExpert.canSource(linkScope.getMetaclass(), fromScope.getMetaclass());
    }

    @objid ("ac44c19a-2c12-495b-b817-757c7e731f1c")
    @Override
    public boolean canTarget(ElementScope linkScope, ElementScope toScope) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkScope.getStereotype());
        MExpert linkExpert = linkScope.getMetaclass().getMetamodel().getMExpert();
        return stereotypeExpert.canTarget(linkScope, toScope) && linkExpert.canTarget(linkScope.getMetaclass(), toScope.getMetaclass());
    }

    @objid ("1adddb59-3a27-491b-9d2b-9a9ad6412421")
    @Override
    public boolean canSource(ElementScope linkScope, MObject from) {
        IMdaExpert stereotypeExpert = this.mdaRegistry.getExpert(linkScope.getStereotype());
        MExpert linkExpert = linkScope.getMetaclass().getMetamodel().getMExpert();
        return stereotypeExpert.canSource(linkScope, from) && linkExpert.canSource(linkScope.getMetaclass(), from.getMClass());
    }

    @objid ("cad84bd1-60ef-42b4-958a-5db78b1a5210")
    @Override
    public boolean isMultiple(Stereotype linkStereotype) {
        IMdaExpert expert = this.mdaRegistry.getExpert(linkStereotype);
        return expert.isMultiple(linkStereotype);
    }

    @objid ("4a4e3b65-3f92-47b5-9a1f-59f35a30bc19")
    @Override
    public Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        IMdaExpert expert = this.mdaRegistry.getExpert(linkStereotype);
        return expert.getPossibleTargetMetaclasses(linkStereotype, sourceMetaclass);
    }

    @objid ("56125867-355d-4532-96ab-615b43d74969")
    @Override
    public Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        IMdaExpert expert = this.mdaRegistry.getExpert(linkStereotype);
        return expert.getPossibleSourceMetaclasses(linkStereotype, targetMetaclass);
    }

    @objid ("7effef59-d73e-4af5-9d5f-7bb8f0568aa8")
    @Override
    public Map<ElementScope, List<ElementScope>> getLinkingRules(Stereotype linkStereotype) {
        IMdaExpert expert = this.mdaRegistry.getExpert(linkStereotype);
        return expert.getLinkingRules(linkStereotype);
    }

    /**
     * Registry to get the expert for a given:
     * <ul>
     * <li>parent element metaclass.</li>
     * <li>link metaclass.</li>
     * <li>stereotype.</li>
     * <li>mobject</li>
     * </ul>
     * Custom experts must implements {@link IMdaExpert} and be registered in the <tt>initialize()</tt> method. Stereotype creation experts can be added with {@linkplain #registerLinkExpert(Stereotype, IMdaExpert)} and removed with
     * {@linkplain #unregisterLinkExpert(Stereotype)}.
     */
    @objid ("524b6f32-7225-4ea7-9cef-2aec207cf0fa")
    private static class MdaExpertRegistry {
        @objid ("1aa7ee72-46df-493a-8a80-5813ae222c3a")
        private final Map<String, IMdaExpert> EXPERTS = new HashMap<>();

        @objid ("54b098d7-5af4-4c53-bf23-c864837320f5")
        private final IModuleRegistry IModuleRegistry;

        /**
         * Get the creation expert for the given stereotype.
         * <p>
         * A <code>null</code> stereotype results in an expert always answering <code>true</code>.
         * </p>
         * <p>
         * An <i>abstract</i> or <i>invalid</i> stereotype results in an expert always answering <code>false</code>.
         * </p>
         * 
         * @param el a stereotype. Might be <code>null</code>.
         * @return the matching creation expert, never <i>null</i>.
         */
        @objid ("02b3342e-f6e4-4fb4-8442-8c52e702c2a9")
        public IMdaExpert getExpert(final Stereotype el) {
            if (el == null) {
                return YesMdaExpert.INSTANCE;
            } else if (el.isIsAbstract() || !el.isValid()) {
                return NoMdaExpert.INSTANCE;
            }
            
            // Go for the cache
            String key = computeKey(el);
            IMdaExpert expert = this.EXPERTS.get(key);
            if (expert == null) {
                // Look for an expert in the module registry
                expert = getModuleExpert(el);
            
                // Store expert in cache
                this.EXPERTS.put(key, expert);
            }
            return expert;
        }

        /**
         * Register a stereotyped element creation expert.
         * 
         * @param ste The stereotype
         * @param expert The creation expert.
         */
        @objid ("5b9a2d3b-f596-4a36-bb9d-92829df6dd9f")
        public void registerLinkExpert(final Stereotype ste, final IMdaExpert expert) {
            this.EXPERTS.put(computeKey(ste), expert);
        }

        /**
         * Remove the creation expert registered for the given stereotype.
         * 
         * @param ste The stereotype to forget.
         */
        @objid ("3211a091-b5a7-4343-b7bc-842e35d5539c")
        public void unregisterLinkExpert(final Stereotype ste) {
            this.EXPERTS.remove(computeKey(ste));
        }

        /**
         * This class has no instances.
         * 
         * @param IModuleRegistry the module registry.
         */
        @objid ("cf802fb8-6abd-49d5-bdab-4e58b4057b51")
        public MdaExpertRegistry(IModuleRegistry IModuleRegistry) {
            this.IModuleRegistry = IModuleRegistry;
        }

        @objid ("a1097bb5-2651-4f12-9c12-dd372c42b510")
        private String computeKey(final Stereotype ste) {
            return ste.getUuid();
        }

        /**
         * Get the {@link IMdaExpert} matching the stereotype in the module registry.
         * <p>
         * If none is found return the {@link YesMdaExpert} singleton.
         * 
         * @param el @return
         */
        @objid ("5d0f5de5-cac0-4215-8ad3-1a9964141b5a")
        private IMdaExpert getModuleExpert(final Stereotype el) {
            ModuleComponent moduleComponent = el.getModule();
            
            if (moduleComponent != null) {
                for (IRTModule module : this.IModuleRegistry.getStartedModules()) {
                    if (moduleComponent.equals(module.getModel())) {
                        IMdaExpert expert = module.getIModule().getMdaExpert(el);
                        if (expert != null) {
                            return expert;
                        }
                    }
                }
            }
            return YesMdaExpert.INSTANCE;
        }

    }

    /**
     * MDA Expert that always answer yes.
     * <p>
     * Please note that it does not even check the metaclasses.
     * 
     * @author cma
     * @since 3.8
     */
    @objid ("be2eb908-4830-4f55-844e-1be20ff42dfe")
    private static class YesMdaExpert implements IMdaExpert {
        @objid ("6e2b7347-d99b-4ade-a8cb-02ce05cd8ffa")
        public static final YesMdaExpert INSTANCE = new YesMdaExpert();

        @objid ("be08679f-38e3-4374-98a8-3a5b8f8fc0b4")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject from, MObject to) {
            return true;
        }

        @objid ("08715bba-2ca7-4738-bb79-8fe913b6194d")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
            return true;
        }

        @objid ("0218171a-dae2-4181-b735-d8d155d1431f")
        @Override
        public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
            return true;
        }

        @objid ("5006bc2e-1538-4211-9b17-1d4bc74a4645")
        @Override
        public boolean canSource(Stereotype linkStereotype, MObject linkMetaclass, MObject from) {
            return true;
        }

        @objid ("0531c46f-c07b-4aa5-bf31-ca2741e05f79")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
            return true;
        }

        @objid ("6d50c2ad-7883-4a22-9948-8b32230b8f94")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MObject linkMetaclass, MObject to) {
            return true;
        }

        @objid ("fc49b254-e51d-4331-bc34-51848dd10a8f")
        @Override
        public boolean canLink(ElementScope linkScope, ElementScope fromScope, ElementScope toScope) {
            return true;
        }

        @objid ("8b747dd2-5881-48cf-9e52-68894f2b34a1")
        @Override
        public boolean canSource(ElementScope linkScope, ElementScope fromScope) {
            return true;
        }

        @objid ("7b428735-d135-498d-bcc1-eba1f662451b")
        @Override
        public boolean canSource(ElementScope linkScope, MObject from) {
            return true;
        }

        @objid ("048a1b7c-64dd-4ef3-b888-09939f403fca")
        @Override
        public boolean canTarget(ElementScope linkScope, ElementScope toScope) {
            return true;
        }

    }

    /**
     * MDA Expert that always answer no.
     * <p>
     * Please note that it does not even check the metaclasses.
     * 
     * @since 3.8
     */
    @objid ("4f618944-eaf6-4f39-91fe-b8cf1bbbe35a")
    private static class NoMdaExpert implements IMdaExpert {
        @objid ("f5e9ee80-4c87-424b-a58a-e68e1ae25ed1")
        public static final NoMdaExpert INSTANCE = new NoMdaExpert();

        @objid ("1d009b58-3b67-4427-b67c-724ea1181165")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject from, MObject to) {
            return false;
        }

        @objid ("aff787cc-467c-429c-a752-f53c8c0f0929")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
            return false;
        }

        @objid ("9f484e16-1380-439a-8e7b-21e06777a9fe")
        @Override
        public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
            return false;
        }

        @objid ("d19150e9-6502-49b9-8402-25037fdeed3d")
        @Override
        public boolean canSource(Stereotype linkStereotype, MObject linkMetaclass, MObject from) {
            return false;
        }

        @objid ("525b897b-ae52-4ad8-930a-a4760ae8fd1e")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
            return false;
        }

        @objid ("7524349a-5d96-4afe-a192-8468762a21df")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MObject linkMetaclass, MObject to) {
            return false;
        }

        @objid ("ce6e9698-c67a-4963-9528-d5e363245ea0")
        @Override
        public boolean canLink(ElementScope linkScope, ElementScope fromScope, ElementScope toScope) {
            return false;
        }

        @objid ("c51f1b62-2372-4160-94a0-1672c001aaf1")
        @Override
        public boolean canSource(ElementScope linkScope, ElementScope fromScope) {
            return false;
        }

        @objid ("6c625d7d-b345-4938-9a8f-9c63519007e1")
        @Override
        public boolean canSource(ElementScope linkScope, MObject from) {
            return false;
        }

        @objid ("22c219b4-ade6-4919-a2ac-00fd9eede988")
        @Override
        public boolean canTarget(ElementScope linkScope, ElementScope toScope) {
            return false;
        }

    }

}
