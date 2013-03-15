/*
 *  Copyright 2011-2013 Blue Lotus Software, LLC.
 *  Copyright 2011-2013 John Yeary <jyeary@bluelotussoftware.com>.
 *
 *  $Id$
 */
package com.bluelotussoftware.example.jsf.richfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import org.richfaces.component.UITree;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.richfaces.event.TreeToggleEvent;
import org.richfaces.model.ClassicTreeNodeDataModelImpl;
import org.richfaces.model.TreeNode;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class TreeBean implements Serializable {

    private static final long serialVersionUID = 3096479824299598082L;
    private NodesTreeSequenceKeyModelImpl<TreeNodeImpl<String>> customModel;
    private ClassicTreeNodeDataModelImpl model;
    private List<TreeNode> nodes;
    private TreeNode selectedNode;

    public TreeBean() {
        model = new ClassicTreeNodeDataModelImpl();
        customModel = new NodesTreeSequenceKeyModelImpl<TreeNodeImpl<String>>();
        nodes = new ArrayList<TreeNode>();

        TreeNodeImpl<String> root = new TreeNodeImpl<String>();

        TreeNodeImpl<String> parent = new TreeNodeImpl<String>();
        parent.setData("John");

        TreeNodeImpl<String> child1 = new TreeNodeImpl<String>();
        child1.setData("Ethan");

        TreeNodeImpl<String> child2 = new TreeNodeImpl<String>();
        child2.setData("Sean");

        root.addChild("John", parent);
        parent.addChild("Ethan", child1);
        parent.addChild("Sean", child2);

        model.setWrappedData(root);

        org.richfaces.model.TreeNodeImpl node = new org.richfaces.model.TreeNodeImpl();
        org.richfaces.model.TreeNodeImpl node2 = new org.richfaces.model.TreeNodeImpl();
        node.addChild("A", node2);
        for (int i = 0; i < 10; i++) {
            org.richfaces.model.TreeNodeImpl v = new org.richfaces.model.TreeNodeImpl();
            node2.addChild(i, v);
            nodes.add(v);
        }

        TreeNodeImpl<String> rNode = new TreeNodeImpl<String>();
        rNode.setData("Root");
        TreeNodeImpl<String> pNode1 = new TreeNodeImpl<String>();
        pNode1.setData("Node 1");
        TreeNodeImpl<String> pNode2 = new TreeNodeImpl<String>();
        pNode2.setData("Node 2");
        TreeNodeImpl<String> pNode3 = new TreeNodeImpl<String>();
        pNode3.setData("Node 3");
        TreeNodeImpl<String> pNode4 = new TreeNodeImpl<String>();
        pNode4.setData("Node 4");
        TreeNodeImpl<String> pNode5 = new TreeNodeImpl<String>();
        pNode5.setData("Node 5");

        rNode.addChild("pNode1", pNode1);
        rNode.addChild("pNode2", pNode2);
        rNode.addChild("pNode3", pNode3);
        rNode.addChild("pNode4", pNode4);
        rNode.addChild("pNode5", pNode5);

        pNode1.addChild("pNode2", pNode2);
//        pNode2.addChild("pNode3", pNode3);
//        pNode3.addChild("pNode4", pNode4);
//        pNode4.addChild("pNode5", pNode5);
//        pNode5.addChild("pNode1", pNode1);

        List<TreeNodeImpl<String>> list = new ArrayList<TreeNodeImpl<String>>();
        list.add(rNode);

        for (int i = 0; i < 10; i++) {
            TreeNodeImpl<String> tNode = new TreeNodeImpl<String>();
            tNode.setData(String.valueOf(i));
            pNode1.addChild(i, tNode);
            pNode2.addChild(i, tNode);
            pNode3.addChild(i, tNode);
            pNode4.addChild(i, tNode);
            pNode5.addChild(i, tNode);
            list.add(tNode);
        }
        customModel.setWrappedData(list);
    }

    public ClassicTreeNodeDataModelImpl getModel() {
        return model;
    }

    public NodesTreeSequenceKeyModelImpl<TreeNodeImpl<String>> getCustomModel() {
        return customModel;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void processTreeSelectionChange(TreeSelectionChangeEvent event) throws AbortProcessingException {
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()), new FacesMessage("TreeSelectionChangeEvent FIRED"));
        List<Object> selection = new ArrayList<Object>(event.getNewSelection());
        Object currentSelectionKey = selection.get(0);
        UITree tree = (UITree) event.getSource();
        Object storedKey = tree.getRowKey();
        tree.setRowKey(currentSelectionKey);
        selectedNode = (TreeNode) tree.getRowData();
        System.out.println("A node was selected: " + ((TreeNodeImpl<String>) selectedNode).getData());
    }

    public void selectionChanged(TreeSelectionChangeEvent event) {
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()), new FacesMessage("TreeSelectionChangeEvent FIRED"));
    }

    public void toggleChanged(TreeToggleEvent event) {
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()), new FacesMessage("TreeToggleEvent FIRED"));
    }

    public void crap() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CRAP EVENT FIRED"));
    }
}
