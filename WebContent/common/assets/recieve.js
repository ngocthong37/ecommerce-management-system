var inputCount = 1;

		function addInput(event) {
			event.preventDefault();
			var newInput = document.createElement('input');
			newInput.type = 'text';
			newInput.name = 'productItem' + inputCount;
			newInput.type = 'text';
			newInput.name = 'qty' + inputCount;
			newInput.type = 'text';
			newInput.name = 'price' + inputCount;
			inputCount++;
			var container = document.getElementById('input-container');
			container.appendChild(newInput);
		}