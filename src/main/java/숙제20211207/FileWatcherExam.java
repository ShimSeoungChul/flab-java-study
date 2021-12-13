package 숙제20211207;

import static java.io.File.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class FileWatcherExam {

	// WatchService에 디렉토리 등록을 의미하는 객체
	static WatchKey watchKey;

	public static void main(String[] args) throws IOException, InterruptedException {

		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 조사할 디렉터리 경로 입력
		String fileDir = separator + "Users" + separator + "ted.sc" + separator + "Desktop" + separator + "watchedDir";
		Path path = Paths.get(fileDir);
		path.register(watchService,
			StandardWatchEventKinds.ENTRY_CREATE,
			StandardWatchEventKinds.ENTRY_DELETE,
			StandardWatchEventKinds.ENTRY_MODIFY,
			StandardWatchEventKinds.OVERFLOW);

		Thread thread = new Thread(()-> {
			try {
				watchKey = watchService.take(); // 이벤트가 발생할 때까지 block된다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			List<WatchEvent<?>> events = watchKey.pollEvents();//이벤트들을 가져옴
			for(WatchEvent<?> event : events) {
				// 가져온 이벤트 종류
				WatchEvent.Kind<?> kind = event.kind();
				// 이벤트가 발생한 경로
				Path paths = (Path)event.context();
				if(kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					System.out.println("디렉토리에 " + paths.getFileName() + "파일이 생성되었습니다.");
				}else if(kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
					System.out.println("디렉토리에 " + paths.getFileName() + "파일이 삭제되었습니다.");
				}else if(kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
					System.out.println("디렉토리에 " + paths.getFileName() + "파일이 수정되었습니다.");
				}else if(kind.equals(StandardWatchEventKinds.OVERFLOW)) {
					System.out.println("이벤트가 손실되거나 삭제되었습니다.");
				}
			}
		});

		thread.start();

		// 1초에 한 번 스레드의 상태를 확인한다. 스레드는 이벤트 감지 후 종료된다.
		for (int i = 0; i < 10000; i++){
			System.out.println(thread.getState());
			Thread.sleep(1000);
		}
	}
}
