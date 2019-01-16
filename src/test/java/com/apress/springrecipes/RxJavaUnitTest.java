package com.apress.springrecipes;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.TestObserver;

/*
 	The build blocks for RxJava code are the following:

observables representing sources of data

subscribers (or observers) listening to the observables

a set of methods for modifying and composing the data

An observable emits items; a subscriber consumes those items.

2.1. Observables
Observables are the sources for the data. Usually they start providing data once a subscriber starts listening. 
An observable may emit any number of items (including zero items). It can terminate either successfully or with an error. 
Sources may never terminate, for example, an observable for a button click can potentially produce an infinite stream of events.

2.2. Subscribers
A observable can have any number of subscribers. If a new item is emitted from the observable, 
the onNext() method is called on each subscriber. If the observable finishes its data flow successful, 
the onComplete() method is called on each subscriber. Similar, if the observable finishes its data flow with an error, 
the onError() method is called on each subscriber.


 */

public class RxJavaUnitTest {
	
	String result = "";
	
	@Test
	public void testReturnAValue() {
		
		result = "";
		Observable<String> observer = Observable.just("Hello");
		observer.subscribe(s->result=s);
		assertTrue(result.equals("Hello"));
	}
	
	@Test 
	public void testExpectNPE() {
		
		Observable<Todo> todoObservable = Observable.create(new ObservableOnSubscribe<Todo>() {

			@Override
			public void subscribe(ObservableEmitter<Todo> emitter) throws Exception {
				try {
					List<Todo> todos = RxJavaUnitTest.this.getTodos();
					
					if(todos == null) {
						throw new NullPointerException("todos was null");
					}
					for (Todo todo : todos) {
						emitter.onNext(todo);
					}
					emitter.onComplete();
					
				}catch(Exception e) {
					emitter.onError(e);
				}
				
			}
		});
		TestObserver<Object> testObserver = new TestObserver<>();
		todoObservable.subscribeWith(testObserver);
		
		System.out.println(testObserver);
		
		testObserver.assertError(NullPointerException.class);
		
		Observable.just("Hello", "World").subscribe(System.out::println);
		List<String> words = Arrays.asList(
				 "the",
				 "quick",
				 "brown",
				 "fox",
				 "jumped",
				 "over",
				 "the",
				 "lazy",
				 "dog"
				);
		//Observable.just(words).subscribe(System.out::println);
		Observable.just(words).subscribe(word->System.out.println(word));
		
		
		Observable.fromIterable(words)
		 .zipWith(Observable.range(1, Integer.MAX_VALUE), 
		    (string, count)->String.format("%2d. %s", count, string))
		 .subscribe(System.out::println);
		
		
	}
	
	private List<Todo> getTodos(){
		return null;
		//return Arrays.asList(new Todo(),new Todo());
	}
	
	public class Todo	{
		
	}

}
