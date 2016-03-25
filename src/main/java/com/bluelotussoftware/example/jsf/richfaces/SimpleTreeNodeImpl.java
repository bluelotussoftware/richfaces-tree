/*
 * Copyright 2014 John Yeary <jyeary@bluelotussoftware.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: SimpleTreeNodeImpl.java,v c14f585e2f2d 2014/10/31 00:16:20 jyeary $
 */
package com.bluelotussoftware.example.jsf.richfaces;

import java.io.Serializable;
import org.richfaces.model.TreeNodeImpl;

/**
 * An implementation of {@link TreeNode} that extends {@link TreeNodeImpl}.
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
public class SimpleTreeNodeImpl extends TreeNodeImpl implements Serializable {

    private static final long serialVersionUID = 2249840550883450144L;

    /**
     * Default constructor.
     */
    public SimpleTreeNodeImpl() {
        super();
    }

    /**
     * Constructor that sets the data payload.
     *
     * @param data The payload to be set.
     */
    public SimpleTreeNodeImpl(final Object data) {
        this();
        this.data = data;
    }

    /**
     * Constructor that sets if this node is a leaf node, and data payload.
     *
     * @param leaf A {@literal boolean} value of {@literal true} indicates this
     * is a leaf node.
     * @param data The data payload to be set.
     */
    public SimpleTreeNodeImpl(final boolean leaf, final Object data) {
        super(leaf);
        this.data = data;
    }

    /**
     * Data payload stored in tree. The data should be {@link Serializable}.
     */
    private Object data;

    /**
     * Getter for data {@code Object} payload stored in tree.
     *
     * @return {@code Object} data payload.
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter for data {@code Object} payload stored in tree.
     *
     * @param data The data payload to be stored in the tree. It should be
     * {@link Serializable}, and should be kept small to reduce the memory
     * footprint.
     */
    public void setData(final Object data) {
        this.data = data;
    }

}
