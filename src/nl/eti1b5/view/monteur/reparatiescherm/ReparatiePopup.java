package nl.eti1b5.view.monteur.reparatiescherm;

import java.util.ArrayList;
import java.util.List;

import nl.eti1b5.controller.MonteurPopupControl;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.model.Reparatie;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.beans.property.SimpleIntegerProperty;

public class ReparatiePopup extends BorderPane{
	
	private VBox labels;
	private VBox textFields;
	private CheckBox reparatieStatus;
	private Button enter;
	private TableView<Materiaal> materialen;
	private TableColumn<Materiaal, String> materiaalNaam;
	private TableColumn<Materiaal, Integer> aantal;
	private Reparatie reparatie;
	
	public ReparatiePopup(Reparatie reparatie, MonteurPopupControl control){
		this.reparatie = reparatie;
		labels = new VBox();
		labels.setPadding(new Insets(4));
		labels.setSpacing(8);
		
		Label repLabel = new Label("Reparatienummer: ");
		Label kentekenLabel = new Label("Kenteken: ");
		Label omschrijvingLabel = new Label("Omschrijving: ");
		Label beginTijdLabel = new Label("Begintijd: ");
		Label eindTijdLabel = new Label("Eindtijd: ");
		Label reparatieStatusLabel = new Label("Reparatiestatus: ");
		Label materiaalLabel = new Label("Materialen: ");
		
		labels.getChildren().addAll(repLabel, kentekenLabel, omschrijvingLabel, beginTijdLabel, eindTijdLabel, reparatieStatusLabel, materiaalLabel);
		
		textFields = new VBox();
		textFields.setPadding(new Insets(4));
		TextField repNr = new TextField(Integer.toString(reparatie.getReparatieNummer()));
		repNr.setEditable(false);
		TextField kenteken= new TextField(reparatie.getKenteken());
		kenteken.setEditable(false);
		TextField omschrijving = new TextField(reparatie.getOmschrijving().toString());
		omschrijving.setEditable(false);
		TextField beginTijd = new TextField("jjjj-mm-dd uu-mm-ss");
		TextField eindTijd = new TextField("jjjj-mm-dd uu-mm-ss");
		CheckBox reparatieStatus = new CheckBox();
		reparatieStatus.selectedProperty().addListener(control);
		textFields.getChildren().addAll(repNr, kenteken, omschrijving, beginTijd, eindTijd, reparatieStatus);
		
		materialen = new TableView<Materiaal>();
		materialen.setEditable(true);
		/*
		 //Create a customer cell factory so that cells can support editing.
        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new EditingCell();
            }
        };
		*/
		materiaalNaam = new TableColumn<Materiaal, String>("Materiaal");
		materiaalNaam.setCellValueFactory(new PropertyValueFactory<Materiaal,String>("naam"));
		materiaalNaam.setMinWidth(150);
		
		aantal = new TableColumn<Materiaal, Integer>("Aantal");
		aantal.setCellValueFactory(new PropertyValueFactory<Materiaal,Integer>("aantalgebruikt"));
		/*
		aantal.setCellFactory(cellFactory);
		aantal.setOnEditCommit(
			    new EventHandler<CellEditEvent<Materiaal, Integer>>() {
			        @Override
			        public void handle(CellEditEvent<Materiaal, Integer> t) {
			            ((Materiaal) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setaantalgebruikt(t.getNewValue());
			            System.out.println("YEAH");
			        }
			    }
			);
			*/
		aantal.setMinWidth(150);
		MateriaalDao materiaalDao = new MateriaalDao();
		ObservableList<Materiaal> oListMateriaal= FXCollections.observableArrayList(materiaalDao.getMateriaal());
		materialen.setItems(oListMateriaal);
		materialen.getColumns().addAll(materiaalNaam, aantal);
		
		enter = new Button("Enter");
		enter.setOnAction(control);
		
		this.setTop(enter);
		this.setLeft(labels);
		this.setCenter(textFields);
		this.setBottom(materialen);
	}
	
	public void addCheckBoxListener(ChangeListener<Boolean> listener){
		reparatieStatus.selectedProperty().addListener(listener);
	}
	
	public void addButtonListener(EventHandler<ActionEvent> listener){
		enter.setOnAction(listener);
	}
	
	public TableView<Materiaal> getMaterialen(){
		return materialen;
	}
	/*
	public class EditingCell extends TableCell<Materiaal, String> {
		
	    private TextField textField;
	    
	    public EditingCell() {
	    }
	    
	    @Override
	    public void startEdit() {
	        super.startEdit();
	        if (textField == null) {
	            createTextField();
	        }
	        setGraphic(textField);
	        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                textField.requestFocus();
	                textField.selectAll();
	            }
	        });
	    }
	    @Override
	    public void cancelEdit() {
	        super.cancelEdit();
	        setText((String) getItem());
	        setContentDisplay(ContentDisplay.TEXT_ONLY);
	    }
	    @Override
	    public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else {
	            if (isEditing()) {
	                if (textField != null) {
	                    textField.setText(getString());
	                }
	                setGraphic(textField);
	                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	            } else {
	                setText(getString());
	                setContentDisplay(ContentDisplay.TEXT_ONLY);
	            }
	        }
	    }
	    private void createTextField() {
	        textField = new TextField(getString());
	        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
	        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent t) {
	                if (t.getCode() == KeyCode.ENTER) {
	                    commitEdit(textField.getText());
	                } else if (t.getCode() == KeyCode.ESCAPE) {
	                    cancelEdit();
	                } else if (t.getCode() == KeyCode.TAB) {
	                    commitEdit(textField.getText());
	                    TableColumn nextColumn = getNextColumn(!t.isShiftDown());
	                    if (nextColumn != null) {
	                        getTableView().edit(getTableRow().getIndex(), nextColumn);
	                    }
	                }
	            }
	        });
	        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	                if (!newValue && textField != null) {
	                    commitEdit(textField.getText());
	                }
	            }
	        });
	    }
	    private String getString() {
	        return getItem() == null ? "" : getItem().toString();
	    }
	    /**
	     *
	     * @param forward true gets the column to the right, false the column to the left of the current column
	     * @return
	     *
	    private TableColumn<Materiaal, ?> getNextColumn(boolean forward) {
	        List<TableColumn<Materiaal, ?>> columns = new ArrayList<>();
	        for (TableColumn<Materiaal, ?> column : getTableView().getColumns()) {
	            columns.addAll(getLeaves(column));
	        }
	        //There is no other column that supports editing.
	        if (columns.size() < 2) {
	            return null;
	        }
	        int currentIndex = columns.indexOf(getTableColumn());
	        int nextIndex = currentIndex;
	        if (forward) {
	            nextIndex++;
	            if (nextIndex > columns.size() - 1) {
	                nextIndex = 0;
	            }
	        } else {
	            nextIndex--;
	            if (nextIndex < 0) {
	                nextIndex = columns.size() - 1;
	            }
	        }
	        return columns.get(nextIndex);
	    }
	     
	    private List<TableColumn<Materiaal, ?>> getLeaves(TableColumn<Materiaal, ?> root) {
	        List<TableColumn<Materiaal, ?>> columns = new ArrayList<>();
	        if (root.getColumns().isEmpty()) {
	            //We only want the leaves that are editable.
	            if (root.isEditable()) {
	                columns.add(root);
	            }
	            return columns;
	        } else {
	            for (TableColumn<Materiaal, ?> column : root.getColumns()) {
	                columns.addAll(getLeaves(column));
	            }
	            return columns;
	        }
	    }
	}
	*/

}
