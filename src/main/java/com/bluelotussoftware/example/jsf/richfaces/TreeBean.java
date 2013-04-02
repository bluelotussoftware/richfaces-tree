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
import java.util.Random;
import java.util.UUID;
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
    private ClassicTreeNodeDataModelImpl model;
    private TreeNodesSequenceKeyModel<TypedTreeNode<String>> customModel;
    private List<TypedTreeNode<String>> customNodes;
    private TreeNode selectedNode;

    public TreeBean() {
        model = new ClassicTreeNodeDataModelImpl();
        customModel = new TreeNodesSequenceKeyModel<TypedTreeNode<String>>();
        customNodes = new ArrayList<TypedTreeNode<String>>();

        //MODEL
        TypedTreeNode<String> root = new TypedTreeNode<String>("Family");
        TypedTreeNode<String> father = new TypedTreeNode<String>("John");
        TypedTreeNode<String> mother = new TypedTreeNode<String>("Patty");
        TypedTreeNode<String> child1 = new TypedTreeNode<String>("Ethan");
        TypedTreeNode<String> child2 = new TypedTreeNode<String>("Sean");
        root.addChild("John", father);
        root.addChild("Patty", mother);
        father.addChild("Ethan", child1);
        father.addChild("Sean", child2);
        mother.addChild("Ethan", child1);
        mother.addChild("Sean", child2);
        model.setWrappedData(root);

        //CUSTOM MODEL
        List<TypedTreeNode<String>> list = new ArrayList<TypedTreeNode<String>>();

        for (int i = 0; i < 10; i++) {
            TypedTreeNode<String> node = new TypedTreeNode<String>();
            node.setData(UUID.randomUUID().toString());
            list.add(node);
        }

        TypedTreeNode<String> customRoot = new TypedTreeNode<String>();
        Random r = new Random();
        for (TypedTreeNode<String> node : list) {

            for (int i = 0; i < list.size(); i++) {
                node.addChild(UUID.randomUUID().toString(), new TypedTreeNode<String>("sub - " + r.nextInt(32768) + " - " + node.getData()));
            }
            customRoot.addChild(node.getData(), node);
        }
        customModel.setWrappedData(customRoot);

        //NODES
        customNodes.addAll(list);
    }

    public ClassicTreeNodeDataModelImpl getModel() {
        return model;
    }

    public TreeNodesSequenceKeyModel<TypedTreeNode<String>> getCustomModel() {
        return customModel;
    }

    public List<TypedTreeNode<String>> getCustomNodes() {
        return customNodes;
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
        System.out.println("A node was selected: " + ((TypedTreeNode<String>) selectedNode).getData());
    }

    public void selectionChanged(TreeSelectionChangeEvent event) {
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()), new FacesMessage("TreeSelectionChangeEvent FIRED"));
    }

    public void toggleChanged(TreeToggleEvent event) {
        FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(FacesContext.getCurrentInstance()), new FacesMessage("TreeToggleEvent FIRED"));
    }
}