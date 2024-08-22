package com.scube.mycognitiv.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exam {
	Document dom;
	public int currentQuestion = 0;
	public int totalNumberOfQuestions = 0;
	public int quizDuration = 0;
	public String selectedExam;
	public String currentTest;
	public String currentLevel;
	public int currentQuestions[];

	/**
	 * @return the currentTest
	 */
	public String getCurrentTest() {
		return currentTest;
	}

	/**
	 * @param currentTest
	 *            the currentTest to set
	 */
	public void setCurrentTest(String currentTest) {
		this.currentTest = currentTest;
	}

	/**
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param currentLevel
	 *            the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	int testIds;

	/**
	 * @return the selectedExam
	 */
	public String getSelectedExam() {
		return selectedExam;
	}

	/**
	 * @param selectedExam
	 *            the selectedExam to set
	 */
	public void setSelectedExam(String selectedExam) {
		this.selectedExam = selectedExam;
	}

	public List<Integer> quizSelectionsList = new ArrayList<Integer>();

	public List<Integer> getQuizSelectionsList() {
		return quizSelectionsList;
	}

	public Map<Integer, Integer> selections = new LinkedHashMap<Integer, Integer>();
	public Map<Integer, Integer[]> selection = new LinkedHashMap<Integer, Integer[]>();

	public ArrayList<QuizQuestion> questionList;

	public Exam(String test, int totalNumberOfQuestions) throws SAXException,
			ParserConfigurationException, IOException, URISyntaxException {
		dom = CreateDOM.getDOM(test);
		for (int i = 0; i < totalNumberOfQuestions; i++) {
			selections.put(i, -1);
		}
		questionList = new ArrayList<QuizQuestion>(totalNumberOfQuestions);

	}

	@SuppressWarnings("unchecked")
	public Exam(HttpServletRequest request) throws SQLException {

		HttpSession session = request.getSession(false);
		questionList = (ArrayList<QuizQuestion>) session
				.getAttribute("examQuestionObjList");
		/*String questiontype=(String)request.getSession(false).getAttribute("questiontype");*/
		
		for (int i = 0; i < questionList.size(); i++) {
			selections.put(i, -1);
		}
		
	}

	public Document getDom() {
		return dom;
	}

	public void setQuestion(int i,HttpServletRequest request) {
		int number = i;
		String options[] = new String[4];
		String question = null;
		int correct = 0;
		String questiontype = null;
		float complexity = 0f;

		NodeList qList = dom.getElementsByTagName("question");
		NodeList childList = qList.item(i).getChildNodes();

		int counter = 0;

		for (int j = 0; j < childList.getLength(); j++) {
			Node childNode = childList.item(j);
			if ("answer".equals(childNode.getNodeName())) {
				options[counter] = childList.item(j).getTextContent();
				counter++;
			} else if ("quizquestion".equals(childNode.getNodeName())) {
				question = childList.item(j).getTextContent();
			} else if ("correct".equals(childNode.getNodeName())) {
				correct = Integer.parseInt(childList.item(j).getTextContent());
			} else if ("questiontype".equals(childNode.getNodeName())) {
				questiontype = childList.item(j).getTextContent();
				
				
			} else if ("complexity".equals(childNode.getNodeName())) {
				complexity = Float.parseFloat(childList.item(j)
						.getTextContent());
			}
		}

		QuizQuestion q = new QuizQuestion();
		q.setQuestionNumber(number);
		q.setQuestion(question);
		q.setCorrectOptionIndex(correct);
		q.setQuestionOptions(options);
		q.setQuestionType(questiontype);
		q.setComplexity(complexity);
		questionList.add(number, q);
		
	
	}

	public ArrayList<QuizQuestion> getQuestionList() {
		return this.questionList;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public Map<Integer, Integer> getSelections() {
		return this.selections;
	}	

	public int getUserSelectionForQuestion(int i) {
		Map<Integer, Integer> map = getSelections();

		return (Integer) map.get(i);
	}

	public int getTotalNumberOfQuestions() {
		return totalNumberOfQuestions;
	}

	public void setTotalNumberOfQuestions(int i) {
		this.totalNumberOfQuestions = i;
	}

	public int[] getCurrentQuestions() {
		return currentQuestions;
	}

	public void setCurrentQuestions(int currentQuestions[]) {
		this.currentQuestions = currentQuestions;
	}

}
