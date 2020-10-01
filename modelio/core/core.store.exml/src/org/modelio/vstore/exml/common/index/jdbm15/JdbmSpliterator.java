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

package org.modelio.vstore.exml.common.index.jdbm15;

import java.io.IOError;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.StreamException;

/**
 * Spliterator that transforms IOError and InternalError to
 * JdbmIndexException  wrapped into {@link StreamException}.
 * @author cma
 * @since 3.6
 * 
 * @param <T>
 */
@objid ("3e17d2ab-7276-4abb-8a2e-20896d3b486b")
class JdbmSpliterator<T> implements Spliterator<T> {
    @objid ("254abd8a-76e9-495b-ba32-761563f0b560")
    private final Spliterator<T> wrapped;

    @objid ("5d25f49e-64b0-4420-9775-935c0cae9fed")
    public JdbmSpliterator(Spliterator<T> wrapped) {
        this.wrapped = wrapped;
    }

    @objid ("147ebc23-0a7d-4b9a-9498-60eb685bbff2")
    @Override
    public boolean tryAdvance(Consumer<? super T> action) throws StreamException {
        try {
            return this.wrapped.tryAdvance(action);
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("01852517-9af0-4863-a65a-56c99784a137")
    @Override
    public Spliterator<T> trySplit() throws StreamException {
        try {
            return new JdbmSpliterator<>(this.wrapped.trySplit());
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("e3825b7f-6995-4123-bdc9-8df57227ae56")
    @Override
    public long estimateSize() throws StreamException {
        try {
            return this.wrapped.estimateSize();
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("7ea0c18c-1936-4e1f-a74d-5445a56c6e81")
    @Override
    public int characteristics() {
        return this.wrapped.characteristics();
    }

    @objid ("ef9393f1-e952-4a24-bc0b-b72b5a07be47")
    @Override
    public Comparator<? super T> getComparator() {
        return this.wrapped.getComparator();
    }

    @objid ("fe9540e4-2e47-4dee-9ac0-693f21a70f3e")
    @Override
    public long getExactSizeIfKnown() throws StreamException {
        try {
            return this.wrapped.getExactSizeIfKnown();
        } catch (InternalError e) {
            throw fail(JdbmIndexException.from(e));
        } catch (IOError e) {
            throw fail(JdbmIndexException.from(e));
        }
    }

    @objid ("9890ad9a-25c1-45bf-a790-ab7dd78e5063")
    private StreamException fail(JdbmIndexException from) {
        return new StreamException(from);
    }

    @objid ("12330a13-be28-424e-ab28-a92a4b41f4cd")
    @Override
    public boolean hasCharacteristics(int characteristics) {
        return this.wrapped.hasCharacteristics(characteristics);
    }

}
