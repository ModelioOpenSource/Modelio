/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.vcore.session.impl.jmx;

import java.lang.management.ManagementFactory;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.cache.CacheManager;
import org.modelio.vcore.smkernel.IKernelServiceProvider;
import org.modelio.vcore.smkernel.ISwap;
import org.modelio.vcore.swap.JdbmSwap;
import org.modelio.vcore.swap.jmx.ISwapMXBean;
import org.modelio.vcore.swap.jmx.JdbmSwapMXAdapter;

/**
 * MBean implementation for CoreSession.
 * <p>
 * This implementation owns an instrumented wrapper of the {@link ISwap} given to the constructor.
 * This wrapper monitors and controls the actual swap as long as
 * the wrapper is used in place of the actual swap.
 */
@objid ("90f4f45d-4935-11e2-91c9-001ec947ccaf")
public class CoreSessionMXBeanImpl implements ICoreSessionMXBean {
    /**
     * {@link ObjectName} name for the CoreSession MBean.
     */
    @objid ("ee971bb4-3b1d-4ff3-bce7-1e136ea703cf")
    private static final String JMX_CORE_SESSION_ID = "org.modelio.jmx:type=CoreSession,id=%d";

    /**
     * {@link ObjectName} name for the Swap MBean.
     */
    @objid ("f3583319-4ccb-4238-8e07-c22134bdc6b5")
    private static final String JMX_CORE_SESSION_SWAP_ID = "org.modelio.jmx:type=CoreSession,id=%d,sub=Swap";

    @objid ("dca6b001-493b-11e2-91c9-001ec947ccaf")
    private ICoreSession session;

    @objid ("dca91255-493b-11e2-91c9-001ec947ccaf")
    private JdbmSwapMXAdapter swap;

    @objid ("dca91256-493b-11e2-91c9-001ec947ccaf")
    private IKernelServiceProvider ksp;

    @objid ("dca91271-493b-11e2-91c9-001ec947ccaf")
    private ObjectName beanName;

    @objid ("dca91272-493b-11e2-91c9-001ec947ccaf")
    private ObjectName swapBeanName;

    @objid ("b2ddf6cd-49c5-11e2-91c9-001ec947ccaf")
    private CacheManager cacheManager;

    /**
     * @param session the core session
     * @param swap the actual swapper
     * @param cacheManager the session cache manager
     */
    @objid ("dca91257-493b-11e2-91c9-001ec947ccaf")
    public CoreSessionMXBeanImpl(ICoreSession session, JdbmSwap swap, CacheManager cacheManager) {
        this.session = session;
        this.swap = new JdbmSwapMXAdapter(swap);
        this.cacheManager = cacheManager;
    }

    @objid ("dca9125b-493b-11e2-91c9-001ec947ccaf")
    @Override
    public int getRepositoryCount() {
        return this.session.getRepositorySupport().getRepositories().size();
    }

    @objid ("dca91260-493b-11e2-91c9-001ec947ccaf")
    @Override
    public ISwapMXBean getSwapBean() {
        return this.swap;
    }

    /**
     * Get the JMX adapter wrapping the actual {@link ISwap} implementation.
     * <p>
     * The returned wrapper also implements {@link ISwapMXBean}.
     * @return the JMX swap adapter.
     */
    @objid ("dca91265-493b-11e2-91c9-001ec947ccaf")
    public ISwap getSwapImpl() {
        return this.swap;
    }

    @objid ("dca91269-493b-11e2-91c9-001ec947ccaf")
    @Override
    public int getKernelId() {
        return this.ksp.getId();
    }

    /**
     * @param ksp the kernel service provider
     */
    @objid ("dca9126e-493b-11e2-91c9-001ec947ccaf")
    public void setKernelServiceProvider(IKernelServiceProvider ksp) {
        this.ksp = ksp;
    }

    /**
     * Register the bean in the platform.
     */
    @objid ("dca91273-493b-11e2-91c9-001ec947ccaf")
    public void register() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        
        try {
            this.beanName = new ObjectName(String.format(JMX_CORE_SESSION_ID,getKernelId()));
            mbs.registerMBean(this, this.beanName);
        
            this.swapBeanName = new ObjectName(String.format(JMX_CORE_SESSION_SWAP_ID,getKernelId()));
            mbs.registerMBean(getSwapBean(), this.swapBeanName);
        } catch (JMException e) {
            throw new Error(e);
        }
    }

    /**
     * Remove this bean and the swap bean from the platform.
     */
    @objid ("dcab74a9-493b-11e2-91c9-001ec947ccaf")
    public void unregister() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.unregisterMBean(this.beanName);
            mbs.unregisterMBean(this.swapBeanName);
        } catch (JMException e) {
            throw new Error(e);
        }
    }

    @objid ("b2e05921-49c5-11e2-91c9-001ec947ccaf")
    @Override
    public int getLoadedCount() {
        return this.cacheManager.asCollection().size();
    }

    @objid ("b2e05925-49c5-11e2-91c9-001ec947ccaf")
    @Override
    public int getDeletedObjectsCount() {
        return this.cacheManager.getDeletedObjects().size();
    }

}
